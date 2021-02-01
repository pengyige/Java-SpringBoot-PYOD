package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.coupon.QueryCouponActivitySolartermPageListDTO;
import top.yigege.model.CouponActivitySolarterm;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券注册活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface ICouponActivitySolartermService extends IService<CouponActivitySolarterm> {

    PageBean queryCouponActivitySolartermPageList(QueryCouponActivitySolartermPageListDTO queryCouponActivitySolartermPageListDTO);
}
