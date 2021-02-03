package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityIntegralPageListDTO;
import top.yigege.model.CouponActivityIntegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponActivityIntegralMapper extends BaseMapper<CouponActivityIntegral> {

    List<CouponActivityIntegral> queryCouponActivityIntegralPageList(QueryCouponActivityIntegralPageListDTO queryCouponActivityIntegralPageListDTO, Page page);
}
