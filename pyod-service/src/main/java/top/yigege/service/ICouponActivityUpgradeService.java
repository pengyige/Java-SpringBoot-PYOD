package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponActivityUpgradePageListDTO;
import top.yigege.model.CouponActivityUpgrade;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券商品活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface ICouponActivityUpgradeService extends IService<CouponActivityUpgrade> {

    PageBean queryCouponActivityUpgradePageList(QueryCouponActivityUpgradePageListDTO queryCouponActivityUpgradePageListDTO);
}
