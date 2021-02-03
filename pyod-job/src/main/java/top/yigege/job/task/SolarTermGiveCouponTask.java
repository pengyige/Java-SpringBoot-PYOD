package top.yigege.job.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.CouponStatusEnum;
import top.yigege.constant.PyodConstant;
import top.yigege.model.CouponActivity;
import top.yigege.model.CouponActivityRegister;
import top.yigege.model.CouponActivitySolarterm;
import top.yigege.model.SysUser;
import top.yigege.model.User;
import top.yigege.model.UserCoupon;
import top.yigege.service.ICouponActivityService;
import top.yigege.service.ICouponActivitySolartermService;
import top.yigege.service.ICouponService;
import top.yigege.service.ISysUserService;
import top.yigege.service.IUserCouponService;
import top.yigege.service.IUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: FestivalGiveCouponTask
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月19日 22:59
 */
@Component
@Slf4j
public class SolarTermGiveCouponTask {

    @Autowired
    ICouponActivityService iCouponActivityService;

    @Autowired
    IUserService iUserService;

    @Autowired
    ICouponService iCouponService;

    @Autowired
    IUserCouponService iUserCouponService;

    @Autowired
    ICouponActivitySolartermService iCouponActivitySolartermService;

    @Autowired
    ISysUserService iSysUserService;

    /**
     * 执行方法
     */
    public void run(){
        log.info("节气送券run...");
        List<SysUser> sysUserList = iSysUserService.querySysUserByRoleNo(PyodConstant.Default.MERCHANT_ROLE_NO);
        sysUserList.forEach(sysUser -> {
            CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(Long.valueOf(sysUser.getUserId()),ActivityTypeEnum.SOLAR_TERM);
            if (null != couponActivity) {
                log.info("开始送券");
                //查询对应券，赠送给用户
                LambdaQueryWrapper<CouponActivitySolarterm> couponActivitySolartermLambdaQueryWrapper = new LambdaQueryWrapper<>();
                couponActivitySolartermLambdaQueryWrapper.eq(CouponActivitySolarterm::getCouponActivityId, couponActivity.getCouponActivityId());
                List<CouponActivitySolarterm> couponActivitySolartermList = iCouponActivitySolartermService.list(couponActivitySolartermLambdaQueryWrapper);
                if (!couponActivitySolartermList.isEmpty()) {
                    List<User> userList = iUserService.queryUserByMerchantId(Long.valueOf(sysUser.getUserId()));
                    List<UserCoupon> totalUserCouponList = new ArrayList<>();
                    Date getDate = new Date();
                    for(User user : userList) {
                        List<UserCoupon> userCouponList = new ArrayList<>();
                        for (CouponActivitySolarterm couponActivitySolarterm : couponActivitySolartermList) {
                            for (int i = 0 ; i < couponActivitySolarterm.getNum(); i++) {
                                UserCoupon userCoupon = new UserCoupon();
                                userCoupon.setUserId(user.getUserId());
                                userCoupon.setVipCardId(user.getVipCardId());
                                userCoupon.setCouponActivityId(couponActivity.getCouponActivityId());
                                userCoupon.setCouponId(couponActivitySolarterm.getCouponId());
                                userCoupon.setExpireTime(iCouponService.queryExpireDate(couponActivitySolarterm.getCouponId(), getDate));
                                userCoupon.setStatus(CouponStatusEnum.AVAILABLE.getCode());
                                userCouponList.add(userCoupon);
                            }
                        }
                        totalUserCouponList.addAll(userCouponList);
                    }
                    //保存
                    iUserCouponService.batchAddUserCoupon(totalUserCouponList);
                    log.info("商家编号:{}，节日送券完成,赠送{}张券",sysUser.getNo(),totalUserCouponList.size());
                }


            }

        });

    }

}
