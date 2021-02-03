package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_vip_card")
public class VipCard extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 会员卡id
     */
    @TableId(value = "vip_card_id", type = IdType.AUTO)
    private Long vipCardId;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 名称
     */
    private String name;

    /**
     * 封面id
     */
    private Long cardCoverId;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
