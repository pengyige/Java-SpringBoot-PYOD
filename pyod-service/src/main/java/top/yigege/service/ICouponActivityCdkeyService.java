package top.yigege.service;

import top.yigege.dto.modules.coupon.AddCouponActivityCdkeyDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityCdkeyPageListDTO;
import top.yigege.model.CouponActivityCdkey;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 优惠券cdkey活动 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponActivityCdkeyService extends IService<CouponActivityCdkey> {

    CouponActivityCdkey queryCouponActivityCdkeyByCdkey(String cdkey);

    void batchAddCdkey(AddCouponActivityCdkeyDTO addCouponActivityCdkeyDTO);

    PageBean queryCouponActivityCdkeyPageList(QueryCouponActivityCdkeyPageListDTO queryCouponActivityCdkeyPageListDTO);
}
