package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.config.RabbitMQConfig;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.CdKeyStatusEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.DictCodeEnum;
import top.yigege.constant.RedisKeyEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.QueryUserCouponPageListDTO;
import top.yigege.dto.modules.userCoupon.GiveCouponResDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityCdkey;
import top.yigege.model.SysDict;
import top.yigege.model.SysUser;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.dao.UserCouponMapper;
import top.yigege.service.ICouponActivityCdkeyService;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponService;
import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.service.IUserCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.service.IUserService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户优惠券 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class UserCouponServiceImpl extends ServiceImpl<UserCouponMapper, UserCoupon> implements IUserCouponService {

    @Resource
    UserCouponMapper userCouponMapper;

    @Autowired
    IRedisService iRedisService;

    @Autowired
    ISysDictService iSysDictService;

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    ICouponActivityCdkeyService iCouponActivityCdkeyService;

    @Autowired
    IUserService iUserService;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public int queryTotalAvailableCouponCount(Long userId) {
        LambdaQueryWrapper<UserCoupon> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserCoupon::getUserId, userId);
        lambdaQueryWrapper.eq(UserCoupon::getStatus, CouponStatusEnum.AVAILABLE.getCode());
        lambdaQueryWrapper.ge(UserCoupon::getExpireTime, new Date());
        return count(lambdaQueryWrapper);
    }

    @Override
    public void batchAddUserCoupon(List<UserCoupon> userCouponList) {
        if (!userCouponList.isEmpty()) {
            saveBatch(userCouponList);
        }
    }

    @Override
    public PageBean queryUserCouponPageList(QueryUserCouponPageListDTO queryUserCouponPageListDTO) {

        Page pageInfo = new Page(queryUserCouponPageListDTO.getPage(),
                queryUserCouponPageListDTO.getPageSize() == 0 ? 10 : queryUserCouponPageListDTO.getPageSize());
        List<UserCoupon> userCouponList = userCouponMapper.queryUserCouponPageList(queryUserCouponPageListDTO,pageInfo);
        return PageUtil.getPageBean(pageInfo, userCouponList);
    }

    @Transactional
    @Override
    public GiveCouponResDTO giveCoupon(Long merchantId,Long userCouponId) {
        GiveCouponResDTO giveCouponResDTO = new GiveCouponResDTO();
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(merchantId,ActivityTypeEnum.FRIEND_GIVE);
        if (null != couponActivity) {
            giveCouponResDTO.setCouponActivityId(couponActivity.getCouponActivityId());
        }else {
            throw new BusinessException(ResultCodeEnum.NO_COUPON_ACTIVITY);
        }


        UserCoupon userCoupon = getById(userCouponId);
        if (null == userCoupon) {
            throw new BusinessException(ResultCodeEnum.NO_USER_COUPON);
        }

        if (!CouponStatusEnum.AVAILABLE.getCode().equals(userCoupon.getStatus()) ||
          userCoupon.getExpireTime().before(new Date())
        ) {
            throw new BusinessException(ResultCodeEnum.GIVE_FAIL);
        }

        //设置过期
       /* String key = "";
        long expireTime = 86400*1000L;//默认一天
        SysDict sysDict = iSysDictService.queryDictByCode(DictCodeEnum.GIVE_USER_COUPON_RETURN_TIME.getCode());
        if (null != sysDict && StringUtils.isNotBlank(sysDict.getCode())) {
            expireTime = Long.parseLong(sysDict.getValue()) * 1000;
        }
        key = RedisKeyEnum.PEA_EXPIRE_EVENT.getKey() + userCoupon.getUserCouponId();
        iRedisService.setObj(key,userCoupon.getUserCouponId(),expireTime);*/
        rabbitTemplate.convertAndSend(RabbitMQConfig.DELAY_EXCHANGE_NAME,RabbitMQConfig.DELAY_QUEUEA_GIVE_COUPON_ROUTING_KEY,userCoupon.getUserCouponId()+"");

        //更新状态
        userCoupon.setStatus(CouponStatusEnum.SEND_UN_PICK.getCode());
        updateById(userCoupon);

        giveCouponResDTO.setUserCouponId(userCouponId);
        return giveCouponResDTO;
    }

    @Override
    public void getCoupon(Long userCouponId, Long couponActivityId, Long userId) {
        UserCoupon userCoupon = getById(userCouponId);
        if (null == userCoupon) {
            throw new BusinessException(ResultCodeEnum.NO_USER_COUPON);
        }

        if (!CouponStatusEnum.SEND_UN_PICK.getCode().equals(userCoupon.getStatus()) ||
                userCoupon.getExpireTime().before(new Date())
        ) {
            throw new BusinessException(ResultCodeEnum.GET_FAIL);
        }

        User user = iUserService.getById(userId);
        if (null == user) {
            throw new BusinessException(ResultCodeEnum.NO_USER);
        }

        addUserCoupon(userCoupon.getUserId(),user.getVipCardId(),couponActivityId,userCoupon.getCouponId(),new Date());
        //更新状态
        userCoupon.setStatus(CouponStatusEnum.SEND_SUCCESS.getCode());
        updateById(userCoupon);
    }

    @Transactional
    @Override
    public void exchangeCDkey(String cdkey, Long userId) {
        User user = iUserService.getById(userId);
        if (null == user) {
            throw new BusinessException(ResultCodeEnum.NO_USER);
        }

        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(user.getMerchantId(),ActivityTypeEnum.CD_KEY);
        if (null == couponActivity) {
            throw new BusinessException(ResultCodeEnum.NO_COUPON_ACTIVITY);
        }

       CouponActivityCdkey couponActivityCdkey = iCouponActivityCdkeyService.queryCouponActivityCdkeyByCdkey(cdkey);
       if (null == couponActivityCdkey) {
           throw new BusinessException(ResultCodeEnum.NOT_FOUND_CDKEY);
       }

       if (CdKeyStatusEnum.INVALID.getCode().equals(couponActivityCdkey.getStatus())) {
           throw new BusinessException(ResultCodeEnum.CDKEY_INVALID);
       }

       if (CdKeyStatusEnum.ALREADY_USED.getCode().equals(couponActivityCdkey.getStatus())) {
           throw new BusinessException(ResultCodeEnum.ALREADY_CDKEY_USED);
       }

       Date pickDate = new Date();
       for (int i = 0 ; i < couponActivityCdkey.getNum(); i++) {
           addUserCoupon(userId
                   ,user.getVipCardId()
                   ,couponActivityCdkey.getCouponActivityId()
                   ,couponActivityCdkey.getCouponId()
                   ,pickDate);
       }

       //更新状态
        couponActivityCdkey.setStatus(CdKeyStatusEnum.ALREADY_USED.getCode());
        couponActivityCdkey.setUserId(userId);
        iCouponActivityCdkeyService.updateById(couponActivityCdkey);

    }

    /**
     * 添加可使用的优惠券
     * @param userId
     * @param couponActivityId
     * @param couponId
     * @param createTime
     */
    public void addUserCoupon(Long userId,Long vipCardId, Long couponActivityId,Long couponId,Date createTime) {
        //添加优惠券
        UserCoupon userCoupon = new UserCoupon();
        userCoupon.setUserId(userId);
        userCoupon.setCouponActivityId(couponActivityId);
        userCoupon.setCouponId(couponId);
        userCoupon.setVipCardId(vipCardId);
        userCoupon.setExpireTime(iCouponService.queryExpireDate(couponId,createTime));
        userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
        save(userCoupon);
    }
}
