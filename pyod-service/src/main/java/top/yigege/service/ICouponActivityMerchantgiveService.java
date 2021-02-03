package top.yigege.service;

import top.yigege.dto.modules.coupon.AddCouponActivityMerchantgiveDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityMerchantgivePageListDTO;
import top.yigege.model.CouponActivityMerchantgive;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 商家发券 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-30
 */
public interface ICouponActivityMerchantgiveService extends IService<CouponActivityMerchantgive> {

    void addCouponActivityMerchantgive(AddCouponActivityMerchantgiveDTO addCouponActivityMerchantgiveDTO);

    PageBean queryCouponActivityMerchantgivePageList(QueryCouponActivityMerchantgivePageListDTO queryCouponActivityMerchantgivePageListDTO);
}
