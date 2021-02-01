package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivitySolartermPageListDTO;
import top.yigege.model.CouponActivitySolarterm;

import java.util.List;

/**
 * <p>
 * 优惠券注册活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface CouponActivitySolartermMapper extends BaseMapper<CouponActivitySolarterm> {

    List<CouponActivitySolarterm> queryCouponActivitySolartermPageList(QueryCouponActivitySolartermPageListDTO queryCouponActivitySolartermPageListDTO, Page pageInfo);
}
