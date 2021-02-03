package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.constant.ActivityStatusEnum;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.dto.modules.coupon.QueryCouponActivityPageListDTO;
import top.yigege.model.Coupon;
import top.yigege.model.CouponActivity;
import top.yigege.dao.CouponActivityMapper;
import top.yigege.model.SysUser;
import top.yigege.service.ICouponActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponActivityServiceImpl extends ServiceImpl<CouponActivityMapper, CouponActivity> implements ICouponActivityService {

    @Resource
    CouponActivityMapper couponActivityMapper;

    @Override
    public boolean isExist(Long merchantId,ActivityTypeEnum activityTypeEnum) {
        LambdaQueryWrapper<CouponActivity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CouponActivity::getMerchantId,merchantId);
        lambdaQueryWrapper.eq(CouponActivity::getActivityType,activityTypeEnum.getCode());
        lambdaQueryWrapper.eq(CouponActivity::getStatus, ActivityStatusEnum.NORMAL.getCode());
        if (null == getOne(lambdaQueryWrapper)) {
            return false;
        }
        return true;
    }

    @Override
    public CouponActivity queryUnderwayActivity(Long merchantId,ActivityTypeEnum activityTypeEnum) {
        LambdaQueryWrapper<CouponActivity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(CouponActivity::getMerchantId,merchantId);
        lambdaQueryWrapper.eq(CouponActivity::getActivityType,activityTypeEnum.getCode());
        lambdaQueryWrapper.eq(CouponActivity::getStatus, ActivityStatusEnum.NORMAL.getCode());
        Date currentDate = new Date();
        lambdaQueryWrapper.le(CouponActivity::getStartTime,currentDate);
        lambdaQueryWrapper.ge(CouponActivity::getEndTime,currentDate);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public PageBean queryCouponActivityPageList(QueryCouponActivityPageListDTO queryCouponActivityPageListDTO) {
        Page pageInfo = new Page(queryCouponActivityPageListDTO.getPage(),
                queryCouponActivityPageListDTO.getPageSize() == 0 ? 10 : queryCouponActivityPageListDTO.getPageSize());
        List<CouponActivity> couponActivityList = couponActivityMapper.queryCouponActivityPageList(queryCouponActivityPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, couponActivityList);
    }
}
