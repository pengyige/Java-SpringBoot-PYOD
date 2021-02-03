package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.couponDeduction.QueryChargeOffRecordPageListDTO;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.model.CouponDeduction;
import top.yigege.dao.CouponDeductionMapper;
import top.yigege.service.ICouponDeductionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券抵扣 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponDeductionServiceImpl extends ServiceImpl<CouponDeductionMapper, CouponDeduction> implements ICouponDeductionService {

    @Resource
    CouponDeductionMapper couponDeductionMapper;

    @Override
    public List<CouponDeduction> queryCouponDedctionByNo(String couponDeductionNo) {
        LambdaQueryWrapper<CouponDeduction> couponDeductionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        couponDeductionLambdaQueryWrapper.eq(CouponDeduction::getCouponDeductionNo,couponDeductionNo);
        return list(couponDeductionLambdaQueryWrapper);
    }

    @Override
    public PageBean queryChargeOffRecordPageList(QueryChargeOffRecordPageListDTO queryChargeOffRecordPageListDTO) {
        Page page = new Page(queryChargeOffRecordPageListDTO.getPage(),
                queryChargeOffRecordPageListDTO.getPageSize());
        List<CouponDeduction> chargeOffRecordPageList = couponDeductionMapper.queryChargeOffRecordPageList(
                queryChargeOffRecordPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, chargeOffRecordPageList);
    }
}
