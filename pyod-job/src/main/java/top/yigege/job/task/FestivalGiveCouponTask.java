package top.yigege.job.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.FestivalTypeEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityFestival;
import top.yigege.model.CouponActivitySolarterm;
import top.yigege.model.SysUser;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.ICouponActivityFestivalService;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponService;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.ISysUserService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;
import top.yigege.util.SolarTermsUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: FestivalGiveCouponTask
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月19日 22:59
 */
@Component
@Slf4j
public class FestivalGiveCouponTask implements ITask {

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    ICouponActivityFestivalService iCouponActivityFestivalService;

    @Autowired
    IUserService iUserService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    ISysUserService iSysUserService;

    @Autowired
    IGenerateIDService iGenerateIDService;

/*    *//**
     * 中秋
     *//*
    public void zhongQiu(){
        run(FestivalTypeEnum.ZHONG_QIU);
    }

    *//**
     * 元旦
     *//*
    public void yuanDan(){
        run(FestivalTypeEnum.YUAN_DAN);
    }

    *//**
     * 双十一
     *//*
    public void shuangShiYi() {
        run(FestivalTypeEnum.SHUANG_SHI_YI);
    }*/

    /**
     * 执行方法
     */
    @Override
    public void run(String param){
        SolarTermsUtil l = new SolarTermsUtil(System.currentTimeMillis());
        if (!l.isFestival()) {
            log.info("不是节日");
            return;
        };

        FestivalTypeEnum festivalTypeEnum = null;
        if (StringUtils.isNotBlank(l.getSFestivalName())) {
            log.info("节日={}",l.getSFestivalName());
            festivalTypeEnum = FestivalTypeEnum.getByDesc(l.getSFestivalName());
        }else if(StringUtils.isNotBlank(l.getLFestivalName())) {
            log.info("节日={}",l.getLFestivalName());
            festivalTypeEnum = FestivalTypeEnum.getByDesc(l.getLFestivalName());
        };

        if (null == festivalTypeEnum) {
            return;
        }
        log.info("{}节日送券run...",festivalTypeEnum.getDesc());
        //获取所有的商家
        List<SysUser> sysUserList = iSysUserService.querySysUserByRoleNo(PyodConstant.Default.MERCHANT_ROLE_NO);
        FestivalTypeEnum finalFestivalTypeEnum = festivalTypeEnum;
        sysUserList.forEach(sysUser -> {
            CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(Long.valueOf(sysUser.getUserId()),ActivityTypeEnum.SOLAR_TERM);
            if (null != couponActivity) {
                //查询对应券，赠送给用户
                LambdaQueryWrapper<CouponActivityFestival> couponActivityFestivalLambdaQueryWrapper = new LambdaQueryWrapper<>();
                couponActivityFestivalLambdaQueryWrapper.eq(CouponActivityFestival::getCouponActivityId, couponActivity.getCouponActivityId());
                couponActivityFestivalLambdaQueryWrapper.eq(CouponActivityFestival::getType, finalFestivalTypeEnum.getCode());
                List<CouponActivityFestival> couponActivityFestivalList = iCouponActivityFestivalService.list(couponActivityFestivalLambdaQueryWrapper);
                if (!couponActivityFestivalList.isEmpty()) {
                    List<User> userList = iUserService.queryUserByMerchantId(Long.valueOf(sysUser.getUserId()));
                    List<UserCoupon> totalUserCouponList = new ArrayList<>();
                    Date getDate = new Date();
                    for(User user : userList) {
                        List<UserCoupon> userCouponList = new ArrayList<>();
                        for (CouponActivityFestival couponActivityFestival : couponActivityFestivalList) {
                            for (int i = 0 ; i < couponActivityFestival.getNum(); i++) {
                                UserCoupon userCoupon = new UserCoupon();
                                userCoupon.setUserId(user.getUserId());
                                userCoupon.setVipCardId(user.getVipCardId());
                                userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                                userCoupon.setCouponId(couponActivityFestival.getCouponId());
                                userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivityFestival.getCouponId(), getDate));
                                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                                userCouponList.add(userCoupon);
                            }
                        }
                        totalUserCouponList.addAll(userCouponList);
                    }
                    //保存
                    iUserCouponService.batchAddUserCoupon(totalUserCouponList);
                    log.info("商家编号:{}，节日:{},送券完成,赠送{}张券",sysUser.getNo(), finalFestivalTypeEnum.getDesc(),totalUserCouponList.size());
                }
            }
        });


    }




}
