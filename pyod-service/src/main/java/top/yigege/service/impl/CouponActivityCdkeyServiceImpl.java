package top.yigege.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.yigege.constant.CdKeyStatusEnum;
import top.yigege.dto.modules.coupon.AddCouponActivityCdkeyDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityCdkeyPageListDTO;
import top.yigege.model.CouponActivityCdkey;
import top.yigege.dao.CouponActivityCdkeyMapper;
import top.yigege.model.CouponActivityUpgrade;
import top.yigege.service.ICouponActivityCdkeyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.service.IGenerateIDService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static top.yigege.constant.BusinessFlagEnum.CDKEY;

/**
 * <p>
 * 优惠券cdkey活动 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class CouponActivityCdkeyServiceImpl extends ServiceImpl<CouponActivityCdkeyMapper, CouponActivityCdkey> implements ICouponActivityCdkeyService {

    @Resource
    CouponActivityCdkeyMapper couponActivityCdkeyMapper;

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Override
    public CouponActivityCdkey queryCouponActivityCdkeyByCdkey(String cdkey) {
        LambdaQueryWrapper<CouponActivityCdkey> couponActivityCdkeyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        couponActivityCdkeyLambdaQueryWrapper.eq(CouponActivityCdkey::getCdkey,cdkey);
        return getOne(couponActivityCdkeyLambdaQueryWrapper);
    }

    @Override
    public void batchAddCdkey(AddCouponActivityCdkeyDTO addCouponActivityCdkeyDTO) {
        List<CouponActivityCdkey> couponActivityCdkeyList = new ArrayList();
        for (int i = 0 ; i < addCouponActivityCdkeyDTO.getCdkeyNum(); i++) {
            CouponActivityCdkey couponActivityCdkey = new CouponActivityCdkey();
            BeanUtil.copyProperties(addCouponActivityCdkeyDTO, couponActivityCdkey);

            couponActivityCdkey.setCdkey(UUID.randomUUID().toString().replace("-","")+iGenerateIDService.getNo(CDKEY.getMsg()));
            couponActivityCdkey.setStatus(CdKeyStatusEnum.NEW.getCode());
            couponActivityCdkeyList.add(couponActivityCdkey);
        }
        saveBatch(couponActivityCdkeyList);
    }

    @Override
    public PageBean queryCouponActivityCdkeyPageList(QueryCouponActivityCdkeyPageListDTO queryCouponActivityCdkeyPageListDTO) {
        Page page = new Page(queryCouponActivityCdkeyPageListDTO.getPage(),
                queryCouponActivityCdkeyPageListDTO.getPageSize());
        List<CouponActivityCdkey> couponActivityCdkeyList = couponActivityCdkeyMapper.queryCouponActivityCdkeyPageList(
                queryCouponActivityCdkeyPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, couponActivityCdkeyList);
    }
}
