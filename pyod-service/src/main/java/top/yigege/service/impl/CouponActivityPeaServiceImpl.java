package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.dao.CouponActivityPeaMapper;
import top.yigege.dto.modules.coupon.QueryCouponActivityPeaPageListDTO;
import top.yigege.model.CouponActivityIntegral;
import top.yigege.model.CouponActivityPea;
import top.yigege.service.ICouponActivityPeaService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券积豆赠券活动 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@Service
public class CouponActivityPeaServiceImpl extends ServiceImpl<CouponActivityPeaMapper, CouponActivityPea> implements ICouponActivityPeaService {

    @Resource
    CouponActivityPeaMapper couponActivityPeaMapper;

    @Override
    public PageBean queryCouponActivityPeaPageList(QueryCouponActivityPeaPageListDTO queryCouponActivityPeaPageListDTO) {
        Page page = new Page(queryCouponActivityPeaPageListDTO.getPage(),
                queryCouponActivityPeaPageListDTO.getPageSize());
        List<CouponActivityPea> couponActivityPeaList = couponActivityPeaMapper.queryCouponActivityPeaPageList(
                queryCouponActivityPeaPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityPeaList);
    }
}
