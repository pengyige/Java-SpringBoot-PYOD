package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityPeaPageListDTO;
import top.yigege.model.CouponActivityPea;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-13
 */
public interface CouponActivityPeaMapper extends BaseMapper<CouponActivityPea> {

    List<CouponActivityPea> queryCouponActivityPeaPageList(QueryCouponActivityPeaPageListDTO queryCouponActivityPeaPageListDTO, Page page);
}
