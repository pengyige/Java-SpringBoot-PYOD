package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityProductPageListDTO;
import top.yigege.model.CouponActivityProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yigege.model.Product;

import java.util.List;

/**
 * <p>
 * 优惠券商品活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponActivityProductMapper extends BaseMapper<CouponActivityProduct> {


    List<CouponActivityProduct> queryCouponActivityProductPageList(QueryCouponActivityProductPageListDTO queryCouponActivityProductPageListDTO, Page pageInfo);
}
