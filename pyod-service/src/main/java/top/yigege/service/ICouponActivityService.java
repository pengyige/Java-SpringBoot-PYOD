package top.yigege.service;

import top.yigege.constant.ActivityTypeEnum;
import top.yigege.dto.modules.coupon.QueryCouponActivityPageListDTO;
import top.yigege.model.CouponActivity;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponActivityService extends IService<CouponActivity> {

    /**
     * 查询赠券活动是否已存在
     * @param activityTypeEnum 活动类型
     * @return
     */
    boolean isExist(Long merchantId,ActivityTypeEnum activityTypeEnum);

    /**
     * 查询进行中赠券活动
     * @param activityTypeEnum
     * @return
     */
    public CouponActivity queryUnderwayActivity(Long merchantId,ActivityTypeEnum activityTypeEnum);

    PageBean queryCouponActivityPageList(QueryCouponActivityPageListDTO queryCouponActivityPageListDTO);
}
