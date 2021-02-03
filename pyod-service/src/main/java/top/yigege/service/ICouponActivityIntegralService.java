package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponActivityIntegralPageListDTO;
import top.yigege.model.CouponActivityIntegral;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponActivityIntegralService extends IService<CouponActivityIntegral> {

    PageBean queryCouponActivityIntegralPageList(QueryCouponActivityIntegralPageListDTO queryCouponActivityIntegralPageListDTO);
}
