package top.yigege.dto.modules.coupon;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: AddCouponActivityDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月25日 17:03
 */
@Data
public class AddCouponActivityDTO {

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     活动类型(1-新用户注册,2-购买商品,3-兑换cdkey,4-好友赠送,5-升级发放,6-生日发放,7-节气发放,8-积分兑换,9-积豆赠券)     */
   @NotNull(message = "活动类型不能为空")
    private Integer activityType;

    /**
     * 活动状态(1-正常,2-暂停)
     */
    @NotNull(message = "活动状态不能为空")
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 活动开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "开始时间不能为空")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "结束时间不能为空")
    private Date endTime;

}
