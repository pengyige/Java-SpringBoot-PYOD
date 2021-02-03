package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityBirthdayPageListDTO;
import top.yigege.model.CouponActivityBirthday;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券生日活动 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
public interface CouponActivityBirthdayMapper extends BaseMapper<CouponActivityBirthday> {

    List<CouponActivityBirthday> queryCouponActivityBirthdayPageList(QueryCouponActivityBirthdayPageListDTO queryCouponActivityBirthdayPageListDTO, Page page);
}
