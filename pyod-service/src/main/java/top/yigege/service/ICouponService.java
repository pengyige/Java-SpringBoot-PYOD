package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponPageListDTO;
import top.yigege.model.Coupon;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponService extends IService<Coupon> {

    /**
     * 查询优惠券到期时间
     * @param couponId
     * @return
     */
    Date queryExpireDate(Long couponId,Date pickDate);

    /**
     * 查询优惠券分页列表
     * @param queryCouponPageListDTO
     * @return
     */
    PageBean queryCouponPageList(QueryCouponPageListDTO queryCouponPageListDTO);

    /**
     * 查询商家所有优惠券
     * @param merchantId
     * @return
     */
    List<Coupon> queryAllCouponList(Long merchantId);
}
