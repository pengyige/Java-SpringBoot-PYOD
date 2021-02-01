package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.coupon.QueryCouponActivityFestivalPageListDTO;
import top.yigege.model.CouponActivityFestival;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券注册活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface ICouponActivityFestivalService extends IService<CouponActivityFestival> {

    PageBean queryCouponActivityFestivalPageList(QueryCouponActivityFestivalPageListDTO queryCouponActivityFestivalPageListDTO);
}
