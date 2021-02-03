package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityRegisterPageListDTO;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityRegister;
import top.yigege.dao.CouponActivityRegisterMapper;
import top.yigege.service.ICouponActivityRegisterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
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
 * @since 2021-01-12
 */
@Service
public class CouponActivityRegisterServiceImpl extends ServiceImpl<CouponActivityRegisterMapper, CouponActivityRegister> implements ICouponActivityRegisterService {

    @Resource
    CouponActivityRegisterMapper couponActivityRegisterMapper;

    @Override
    public CouponActivityRegister queryCouponActivityRegisterWithCoupon(Long couponActivityRegisterId) {
        return couponActivityRegisterMapper.queryCouponActivityRegisterWithCoupon(couponActivityRegisterId);
    }

    @Override
    public PageBean queryCouponActivityRegisterPageList(QueryCouponActivityRegisterPageListDTO queryCouponActivityRegisterPageListDTO) {
        Page pageInfo = new Page(queryCouponActivityRegisterPageListDTO.getPage(),
                queryCouponActivityRegisterPageListDTO.getPageSize() == 0 ? 10 : queryCouponActivityRegisterPageListDTO.getPageSize());
        List<CouponActivityRegister> couponActivityRegisterList = couponActivityRegisterMapper.queryCouponActivityRegisterPageList(queryCouponActivityRegisterPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, couponActivityRegisterList);
    }
}
