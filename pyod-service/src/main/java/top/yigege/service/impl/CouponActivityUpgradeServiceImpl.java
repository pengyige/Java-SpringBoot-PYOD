package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityUpgradePageListDTO;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.dao.CouponActivityUpgradeMapper;
import top.yigege.service.ICouponActivityUpgradeService;
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
 * @since 2021-01-22
 */
@Service
public class CouponActivityUpgradeServiceImpl extends ServiceImpl<CouponActivityUpgradeMapper, CouponActivityUpgrade> implements ICouponActivityUpgradeService {

    @Resource
    CouponActivityUpgradeMapper couponActivityUpgradeMapper;

    @Override
    public PageBean queryCouponActivityUpgradePageList(QueryCouponActivityUpgradePageListDTO queryCouponActivityUpgradePageListDTO) {
        Page page = new Page(queryCouponActivityUpgradePageListDTO.getPage(),
                queryCouponActivityUpgradePageListDTO.getPageSize());
        List<CouponActivityUpgrade> couponActivityUpgradeList = couponActivityUpgradeMapper.queryCouponActivityUpgradePageList(
                queryCouponActivityUpgradePageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityUpgradeList);
    }
}
