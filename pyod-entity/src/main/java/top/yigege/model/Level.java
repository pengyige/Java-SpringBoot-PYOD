package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_level")
public class Level extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 等级id
     */
    @TableId
    private Long levelId;

    /**
     * 商家Id
     */
    private Long merchantId;

    /**
     * 等级名称
     */
    private String name;

    /**
     * 图片
     */
    private String imageUrl;

    /**
     * 达成条件最小值
     */
    private Integer minValue;

    /**
     * 达成条件最大值
     */
    private Integer maxValue;

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

    @TableField(exist = false)
    List<LevelWelfare> levelWelfareList = new ArrayList<>();

}
