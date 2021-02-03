package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityPageListDTO;
import top.yigege.model.CouponActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponActivityMapper extends BaseMapper<CouponActivity> {

    List<CouponActivity> queryCouponActivityPageList(QueryCouponActivityPageListDTO queryCouponActivityPageListDTO, Page pageInfo);
}
