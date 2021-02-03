package top.yigege.service;

import top.yigege.dto.modules.couponDeduction.QueryChargeOffRecordPageListDTO;
import top.yigege.model.CouponDeduction;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 优惠券抵扣 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ICouponDeductionService extends IService<CouponDeduction> {

    List<CouponDeduction> queryCouponDedctionByNo(String couponDeductionNo);

    PageBean queryChargeOffRecordPageList(QueryChargeOffRecordPageListDTO queryChargeOffRecordPageListDTO);
}
