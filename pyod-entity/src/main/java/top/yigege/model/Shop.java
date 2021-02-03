package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("t_shop")
public class Shop extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺id
     */
    @TableId(value = "shop_id", type = IdType.AUTO)
    private Long shopId;

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 工作模式(0-周一至周日,1-周一至周五,2-周一至周六,3-周末)
     */
    private Integer workMode;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 封面
     */
    private String pic;

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

    /**
     * 距离
     */
    @TableField(exist = false)
    double distance;

    /**
     * 标签
     */
    @TableField(exist = false)
    List<Label> labelList = new ArrayList<>();


}
