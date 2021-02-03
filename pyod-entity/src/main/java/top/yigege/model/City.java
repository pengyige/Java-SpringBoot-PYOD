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
 * 城市
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@TableName("t_city")
@Data
public class City extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 城市id
     */
    @TableId(value = "city_id", type = IdType.AUTO)
    private Long cityId;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 城市名
     */
    private String name;

    /**
     * 类型(0-国家,1-省,2-市,3-县)
     */
    private Integer type;

    /**
     * 热门城市(0-非热门,1-热门)
     */
    private Integer hotFlag;

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
