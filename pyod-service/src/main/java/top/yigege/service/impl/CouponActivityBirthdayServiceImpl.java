package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.coupon.QueryCouponActivityBirthdayPageListDTO;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityBirthday;
import top.yigege.dao.CouponActivityBirthdayMapper;
import top.yigege.model.CouponActivityCdkey;
import top.yigege.service.ICouponActivityBirthdayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 优惠券生日活动 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@Service
public class CouponActivityBirthdayServiceImpl extends ServiceImpl<CouponActivityBirthdayMapper, CouponActivityBirthday> implements ICouponActivityBirthdayService {

    @Resource
    CouponActivityBirthdayMapper couponActivityBirthdayMapper;

    @Override
    public PageBean queryCouponActivityBirthdayPageList(QueryCouponActivityBirthdayPageListDTO queryCouponActivityBirthdayPageListDTO) {
        Page page = new Page(queryCouponActivityBirthdayPageListDTO.getPage(),
                queryCouponActivityBirthdayPageListDTO.getPageSize());
        List<CouponActivityBirthday> couponActivityBirthdayList = couponActivityBirthdayMapper.queryCouponActivityBirthdayPageList(
                queryCouponActivityBirthdayPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityBirthdayList);
    }
}
