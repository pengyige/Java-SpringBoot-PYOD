package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityMerchantgivePageListDTO;
import top.yigege.model.CouponActivityMerchantgive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商家发券 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-30
 */
public interface CouponActivityMerchantgiveMapper extends BaseMapper<CouponActivityMerchantgive> {


    List<CouponActivityMerchantgive> queryCouponActivityMerchantgivePageList(QueryCouponActivityMerchantgivePageListDTO queryCouponActivityMerchantgivePageListDTO, Page page);
}
