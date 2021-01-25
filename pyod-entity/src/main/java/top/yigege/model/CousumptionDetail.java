package top.yigege.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-23
 */
@TableName("t_cousumption_detail")
public class CousumptionDetail extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 消费记录详情id
     */
    private Long cousumptionDetailId;

    /**
     * 消费记录id
     */
    private Long cousumptionRecordId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 商品id
     */
    private Long shopProductId;

    /**
     * 商品单价
     */
    private Double price;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 优惠金额
     */
    private Double discountPrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    public Long getCousumptionDetailId() {
        return cousumptionDetailId;
    }

    public void setCousumptionDetailId(Long cousumptionDetailId) {
        this.cousumptionDetailId = cousumptionDetailId;
    }
    public Long getCousumptionRecordId() {
        return cousumptionRecordId;
    }

    public void setCousumptionRecordId(Long cousumptionRecordId) {
        this.cousumptionRecordId = cousumptionRecordId;
    }
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    public Long getShopProductId() {
        return shopProductId;
    }

    public void setShopProductId(Long shopProductId) {
        this.shopProductId = shopProductId;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
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
        return "CousumptionDetail{" +
        "cousumptionDetailId=" + cousumptionDetailId +
        ", cousumptionRecordId=" + cousumptionRecordId +
        ", orderNo=" + orderNo +
        ", shopProductId=" + shopProductId +
        ", price=" + price +
        ", num=" + num +
        ", discountPrice=" + discountPrice +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
