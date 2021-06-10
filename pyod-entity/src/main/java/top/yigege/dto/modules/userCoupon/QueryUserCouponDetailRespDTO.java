package top.yigege.dto.modules.userCoupon;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: QueryUserCouponDetailRespDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年06月07日 10:31
 */
@Data
public class QueryUserCouponDetailRespDTO {

    /**
     * 优惠券名称
     */
    String couponName;

    /**
     * 过期时间
     */
    Date expireTime;

    /**
     * 是否可领取 true-能点击;false-不可点击
     */
    boolean giveFlag;
}
