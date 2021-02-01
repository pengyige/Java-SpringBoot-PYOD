package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.coupon.QueryCouponActivityPeaPageListDTO;
import top.yigege.model.CouponActivityPea;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券积豆赠券活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface ICouponActivityPeaService extends IService<CouponActivityPea> {

    PageBean queryCouponActivityPeaPageList(QueryCouponActivityPeaPageListDTO queryCouponActivityPeaPageListDTO);
}
