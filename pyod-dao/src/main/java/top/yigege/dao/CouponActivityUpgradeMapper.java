package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityUpgradePageListDTO;
import top.yigege.model.CouponActivityUpgrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券商品活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface CouponActivityUpgradeMapper extends BaseMapper<CouponActivityUpgrade> {

    List<CouponActivityUpgrade> queryCouponActivityUpgradePageList(QueryCouponActivityUpgradePageListDTO queryCouponActivityUpgradePageListDTO, Page page);
}
