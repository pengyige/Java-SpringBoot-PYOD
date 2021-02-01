package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.dao.CouponActivityFestivalMapper;
import top.yigege.dto.modules.coupon.QueryCouponActivityFestivalPageListDTO;
import top.yigege.model.CouponActivityFestival;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.service.ICouponActivityFestivalService;
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
public class CouponActivityFestivalServiceImpl extends ServiceImpl<CouponActivityFestivalMapper, CouponActivityFestival> implements ICouponActivityFestivalService {

    @Resource
    CouponActivityFestivalMapper couponActivityFestivalMapper;

    @Override
    public PageBean queryCouponActivityFestivalPageList(QueryCouponActivityFestivalPageListDTO queryCouponActivityFestivalPageListDTO) {
        Page page = new Page(queryCouponActivityFestivalPageListDTO.getPage(),
                queryCouponActivityFestivalPageListDTO.getPageSize());
        List<CouponActivityFestival> couponActivityFestivalList = couponActivityFestivalMapper.queryCouponActivityFestivalPageList(
                queryCouponActivityFestivalPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityFestivalList);
    }
}
