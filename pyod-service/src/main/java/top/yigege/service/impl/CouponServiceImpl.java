package top.yigege.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.constant.IndateTypeEnum;
import top.yigege.dto.modules.coupon.QueryCouponPageListDTO;
import top.yigege.model.Coupon;
import top.yigege.dao.CouponMapper;
import top.yigege.model.Level;
import top.yigege.service.ICouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 优惠券 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements ICouponService {

    @Resource
    CouponMapper couponMapper;

    @Override
    public Date queryExpireDate(Long couponId,Date pickDate) {
        Coupon coupon = getById(couponId);
        Date expireDate = null;
        if (IndateTypeEnum.FIX.getCode().equals(coupon.getIndateType())) {
            expireDate = coupon.getIndateEndTime();
        }else {
            if (null != pickDate) {
                expireDate = DateUtil.offsetDay(pickDate, coupon.getDuration());
            }
        }
        return expireDate;
    }

    @Override
    public PageBean queryCouponPageList(QueryCouponPageListDTO queryCouponPageListDTO) {
        Page pageInfo = new Page(queryCouponPageListDTO.getPage(),
                queryCouponPageListDTO.getPageSize() == 0 ? 10 : queryCouponPageListDTO.getPageSize());
        List<Coupon> couponList = couponMapper.queryCouponPageList(queryCouponPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, couponList);
    }

    @Override
    public List<Coupon> queryAllCouponList(Long merchantId) {
        LambdaQueryWrapper<Coupon> couponLambdaQueryWrapper = new LambdaQueryWrapper<>();
        couponLambdaQueryWrapper.eq(Coupon::getMerchantId,merchantId);
        return list(couponLambdaQueryWrapper);
    }
}
