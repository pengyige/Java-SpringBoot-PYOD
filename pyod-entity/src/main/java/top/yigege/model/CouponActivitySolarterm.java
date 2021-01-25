package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * <p>
 * 优惠券注册活动
 * </p>
 *
 * @author yigege
 * @since 2021-01-22
 */
@TableName("t_coupon_activity_solarterm")
public class CouponActivitySolarterm extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券活动节气id
     */
    @TableId(value = "coupon_activity_register_id", type = IdType.AUTO)
    private Long couponActivityRegisterId;

    /**
     * 活动id
     */
    private Long couponActivityId;

    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Long getCouponActivityRegisterId() {
        return couponActivityRegisterId;
    }

    public void setCouponActivityRegisterId(Long couponActivityRegisterId) {
        this.couponActivityRegisterId = couponActivityRegisterId;
    }
    public Long getCouponActivityId() {
        return couponActivityId;
    }

    public void setCouponActivityId(Long couponActivityId) {
        this.couponActivityId = couponActivityId;
    }
    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "CouponActivitySolarterm{" +
        "couponActivityRegisterId=" + couponActivityRegisterId +
        ", couponActivityId=" + couponActivityId +
        ", couponId=" + couponId +
        ", num=" + num +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
