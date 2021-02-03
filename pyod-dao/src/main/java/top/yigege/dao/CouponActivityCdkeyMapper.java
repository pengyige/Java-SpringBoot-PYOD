package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityCdkeyPageListDTO;
import top.yigege.model.CouponActivityCdkey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券cdkey活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponActivityCdkeyMapper extends BaseMapper<CouponActivityCdkey> {

    List<CouponActivityCdkey> queryCouponActivityCdkeyPageList(QueryCouponActivityCdkeyPageListDTO queryCouponActivityCdkeyPageListDTO, Page page);
}
