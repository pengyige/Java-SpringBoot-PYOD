package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityRegisterPageListDTO;
import top.yigege.model.CouponActivityRegister;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券注册活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponActivityRegisterMapper extends BaseMapper<CouponActivityRegister> {

    CouponActivityRegister queryCouponActivityRegisterWithCoupon(Long couponActivityRegisterId);

    List<CouponActivityRegister> queryCouponActivityRegisterPageList(QueryCouponActivityRegisterPageListDTO queryCouponActivityRegisterPageListDTO, Page pageInfo);
}
