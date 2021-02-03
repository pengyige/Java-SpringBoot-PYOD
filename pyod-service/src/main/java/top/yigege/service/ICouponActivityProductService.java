package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryCouponActivityProductPageListDTO;
import top.yigege.model.CouponActivityProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.Product;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 优惠券商品活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponActivityProductService extends IService<CouponActivityProduct> {


    PageBean queryCouponActivityProductPageList(QueryCouponActivityProductPageListDTO queryCouponActivityProductPageListDTO);
}
