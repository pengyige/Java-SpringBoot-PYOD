package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 卡片封面
 * </p>
 *
 * @author yigege
 * @since 2021-01-04
 */
@Data
@TableName("t_card_cover")
public class CardCover extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 卡片封面id
     */
    @TableId(value = "card_cover_id", type = IdType.AUTO)
    private Long cardCoverId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 封面地址
     */
    private String imageUrl;

    /**
     * 排序
     */
    private Integer sort;

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
