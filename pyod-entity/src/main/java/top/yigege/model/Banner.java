package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class Banner extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 轮播id
     */
    @TableId(value = "banner_id", type = IdType.AUTO)
    private Long bannerId;

    /**
     * 图片Url
     */
    private String imageUrl;

    /**
     * 跳转url
     */
    private String linkUrl;

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
