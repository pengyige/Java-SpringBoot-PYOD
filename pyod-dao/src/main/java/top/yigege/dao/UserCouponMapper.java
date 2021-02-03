package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryUserCouponPageListDTO;
import top.yigege.model.UserCoupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户优惠券 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface UserCouponMapper extends BaseMapper<UserCoupon> {

    List<UserCoupon> queryUserCouponPageList(QueryUserCouponPageListDTO queryUserCouponPageListDTO, Page pageInfo);
}
