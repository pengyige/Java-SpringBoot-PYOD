package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.dao.CouponActivitySolartermMapper;
import top.yigege.dto.modules.coupon.QueryCouponActivitySolartermPageListDTO;
import top.yigege.model.CouponActivityRegister;
import top.yigege.model.CouponActivitySolarterm;
import top.yigege.service.ICouponActivitySolartermService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券注册活动 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@Service
public class CouponActivitySolartermServiceImpl extends ServiceImpl<CouponActivitySolartermMapper, CouponActivitySolarterm> implements ICouponActivitySolartermService {

    @Resource
    CouponActivitySolartermMapper couponActivitySolartermMapper;

    @Override
    public PageBean queryCouponActivitySolartermPageList(QueryCouponActivitySolartermPageListDTO queryCouponActivitySolartermPageListDTO) {
        Page pageInfo = new Page(queryCouponActivitySolartermPageListDTO.getPage(),
                queryCouponActivitySolartermPageListDTO.getPageSize() == 0 ? 10 : queryCouponActivitySolartermPageListDTO.getPageSize());
        List<CouponActivitySolarterm> couponActivitySolartermList = couponActivitySolartermMapper.queryCouponActivitySolartermPageList(queryCouponActivitySolartermPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, couponActivitySolartermList);
    }
}
