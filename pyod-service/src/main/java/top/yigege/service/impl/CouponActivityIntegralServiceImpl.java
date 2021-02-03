package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityIntegralPageListDTO;
import top.yigege.model.CouponActivityBirthday;
import top.yigege.model.CouponActivityIntegral;
import top.yigege.dao.CouponActivityIntegralMapper;
import top.yigege.service.ICouponActivityIntegralService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponActivityIntegralServiceImpl extends ServiceImpl<CouponActivityIntegralMapper, CouponActivityIntegral> implements ICouponActivityIntegralService {

    @Resource
    CouponActivityIntegralMapper couponActivityIntegralMapper;

    @Override
    public PageBean queryCouponActivityIntegralPageList(QueryCouponActivityIntegralPageListDTO queryCouponActivityIntegralPageListDTO) {
        Page page = new Page(queryCouponActivityIntegralPageListDTO.getPage(),
                queryCouponActivityIntegralPageListDTO.getPageSize());
        List<CouponActivityIntegral> couponActivityIntegralList = couponActivityIntegralMapper.queryCouponActivityIntegralPageList(
                queryCouponActivityIntegralPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityIntegralList);
    }
}
