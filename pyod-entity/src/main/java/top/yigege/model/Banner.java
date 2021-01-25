package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
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
 * @since 2021-01-04
 */
@Data
@TableName("t_banner")
@ApiModel("轮播实体")
public class Banner extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播id
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    @ApiModelProperty("轮播id")
    private Long bannerId;

    /**
     * 商家id
     */
    @ApiModelProperty("商家id")
    private Long merchantId;

    /**
     * 图片Url
     */
    @ApiModelProperty("图片Url")
    private String imageUrl;

    /**
     * 跳转url
     */
    @ApiModelProperty("跳转url")
    private String linkUrl;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
