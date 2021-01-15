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
 * @since 2021-01-05
 */
@Data
@TableName("t_user")
@ApiModel(value = "用户实体")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 性别(1-男,2-女)
     */
    @ApiModelProperty(value = "性别(1-男,2-女)")
    private Integer sex;

    /**
     * 豆豆数量
     */
    @ApiModelProperty(value = "豆豆数量")
    private Integer avaliablePeaNum;

    /**
     * 总共获得豆豆数量
     */
    @ApiModelProperty(value = "总共获得豆豆数量")
    private Integer totalPeaNum;

    /**
     * vip卡片id(主卡)
     */
    @ApiModelProperty(value = "vip卡片id(主卡)")
    private Long vipCardId;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    private String openid;

    /**
     * 微信unionid
     */
    @ApiModelProperty(value = "微信unionid")
    private String unionid;

    /**
     * 可用积分
     */
    @ApiModelProperty(value = "avaliableIntegrate")
    private Integer avaliableIntegrate;

    /**
     * 累计积分
     */
    @ApiModelProperty(value = "累计积分")
    private Integer totalIntegrate;

    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id")
    private Long levelId;


    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id")
    private Long cityId;

    /**
     * 生日
     */
    @ApiModelProperty(value = "生日")
    private Date birthday;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 到期时间
     */
    @ApiModelProperty(value = "到期时间")
    private Date expireTime;

}
