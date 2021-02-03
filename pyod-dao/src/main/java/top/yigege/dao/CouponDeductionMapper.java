package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.couponDeduction.QueryChargeOffRecordPageListDTO;
import top.yigege.model.CouponDeduction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 优惠券抵扣 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface CouponDeductionMapper extends BaseMapper<CouponDeduction> {

    List<CouponDeduction> queryChargeOffRecordPageList(QueryChargeOffRecordPageListDTO queryChargeOffRecordPageListDTO, Page page);
}
