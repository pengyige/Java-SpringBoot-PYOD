package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponPageListDTO;
import top.yigege.model.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponMapper extends BaseMapper<Coupon> {

    List<Coupon> queryCouponPageList(QueryCouponPageListDTO queryCouponPageListDTO, Page pageInfo);
}
