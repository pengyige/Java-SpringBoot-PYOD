package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponActivityRegisterPageListDTO;
import top.yigege.model.CouponActivityRegister;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 优惠券注册活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponActivityRegisterService extends IService<CouponActivityRegister> {

    CouponActivityRegister queryCouponActivityRegisterWithCoupon(Long couponActivityRegisterId);

    PageBean queryCouponActivityRegisterPageList(QueryCouponActivityRegisterPageListDTO queryCouponActivityRegisterPageListDTO);
}
