package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityProductPageListDTO;
import top.yigege.model.CouponActivityProduct;
import top.yigege.dao.CouponActivityProductMapper;
import top.yigege.model.Level;
import top.yigege.model.Product;
import top.yigege.service.ICouponActivityProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券商品活动 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponActivityProductServiceImpl extends ServiceImpl<CouponActivityProductMapper, CouponActivityProduct> implements ICouponActivityProductService {

    @Resource
    CouponActivityProductMapper couponActivityProductMapper;

    @Override
    public PageBean queryCouponActivityProductPageList(QueryCouponActivityProductPageListDTO queryCouponActivityProductPageListDTO) {
        Page pageInfo = new Page(queryCouponActivityProductPageListDTO.getPage(),
                queryCouponActivityProductPageListDTO.getPageSize() == 0 ? 10 : queryCouponActivityProductPageListDTO.getPageSize());
        List<CouponActivityProduct> couponActivityProductList = couponActivityProductMapper.queryCouponActivityProductPageList(queryCouponActivityProductPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, couponActivityProductList);
    }
}
