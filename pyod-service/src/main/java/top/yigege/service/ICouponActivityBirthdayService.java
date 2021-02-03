package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponActivityBirthdayPageListDTO;
import top.yigege.model.CouponActivityBirthday;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券生日活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface ICouponActivityBirthdayService extends IService<CouponActivityBirthday> {

    PageBean queryCouponActivityBirthdayPageList(QueryCouponActivityBirthdayPageListDTO queryCouponActivityBirthdayPageListDTO);
}
