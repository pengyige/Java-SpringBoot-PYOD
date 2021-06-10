package top.yigege.service;

import top.yigege.dto.modules.coupon.QueryUserCouponPageListDTO;
import top.yigege.dto.modules.userCoupon.GiveCouponResDTO;
import top.yigege.dto.modules.userCoupon.QueryUserCouponDetailRespDTO;
import top.yigege.model.UserCoupon;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 用户优惠券 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IUserCouponService extends IService<UserCoupon> {


    int queryTotalAvailableCouponCount(Long userId);

    void batchAddUserCoupon(List<UserCoupon> userCouponList);

    /**
     * 查询用户所有优惠券分页列表
     * @param queryUserCouponPageListDTO
     * @return
     */
    PageBean queryUserCouponPageList(QueryUserCouponPageListDTO queryUserCouponPageListDTO);

    /**
     * 赠送vip
     * @param userCouponId
     */
    public GiveCouponResDTO giveCoupon(Long merchantId,Long userCouponId) ;

    /**
     * 领取优惠券
     * @param userCouponId
     * @param couponActivityId
     * @param userId
     */
    void getCoupon(Long userCouponId, Long couponActivityId, Long userId);

    /**
     * 兑换cdkey
     * @param cdkey
     * @param userId
     */
    void exchangeCDkey(String cdkey, Long userId);


    /**
     * 查询用户优惠券详情
     * @param userCouponId
     * @return
     */
    QueryUserCouponDetailRespDTO queryUserCouponDetail(Long userCouponId);
}
