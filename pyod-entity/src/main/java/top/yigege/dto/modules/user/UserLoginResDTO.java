package top.yigege.dto.modules.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UserLoginResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 17:51
 */
@Data
@ApiModel("用户登入返回实体")
public class UserLoginResDTO {

    /**
     * token
     */
    @ApiModelProperty(value = "token",dataType = "string",example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTA3NTQwNTAsImlhdCI6MTYxMDUzODA1MCwidXNlcklkIjozfQ.Kr-JA3m_hkCzLz0DPB7H3eU9bXCXVGx60yw71bop9Pg")
    private String token;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称",dataType = "string",example = "NYY")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像",dataType = "string",required = true,example = "https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLoOaQOXo4SfBk0SGMTuM2iba393APJb18vhHpBB4zRicd1jpSfY3fL3Pg9DFMyoCG3dUvRZP6qX0wQ/132")
    private String avatar;

    /**
     * 性别(1-男,2-女)
     */
    @ApiModelProperty(value = "性别(1-男,2-女)",dataType = "int",required = true,example = "2")
    private Integer sex;

    /**
     * 豆豆数量
     */
    @ApiModelProperty(value = "豆豆数量",dataType = "double",required = true,example = "0")
    private double avaliablePeaNum;

    /**
     * 总共获得豆豆数量
     */
    @ApiModelProperty(value = "总共获得豆豆数量",dataType = "double",required = true,example = "0")
    private double totalPeaNum;

    /**
     * vip卡片id(主卡)
     */
    @ApiModelProperty(value = "vip卡片id(主卡)",dataType = "int",required = true,example = "1")
    private Long vipCardId;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid",dataType = "string",required = true,example = "ojWgU5dNVC0IhdKpczE3YU7dMa2I")
    private String openid;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号",dataType = "string",required = true,example = "18186424431")
    private String mobile = "";

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 可用积分
     */
    @ApiModelProperty(value = "可用积分",dataType = "int",required = true,example = "0")
    private Integer avaliableIntegrate;

    /**
     * 累计积分
     */
    @ApiModelProperty(value = "累计积分",dataType = "int",required = true,example = "0")
    private Integer totalIntegrate;

    /**
     * 注册时间
     */
    @ApiModelProperty(value = "注册时间",dataType = "string",required = true,example = "2021-01-13T11:38:31.000+0000")
    private Date createTime;


    /**
     * 到期时间
     */
    @ApiModelProperty(value = "到期时间",dataType = "string",required = true,example = "2021-01-13T11:39:31.000+0000")
    private Date expireTime;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度",dataType = "double",required = true,example = "0")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度",dataType = "double",required = true,example = "0")
    private Double latitude;

    /**
     * 城市id
     */
    @ApiModelProperty(value = "城市id",dataType = "int",required = true,example = "77")
    private Long cityId;

    /**
     * 等级id
     */
    @ApiModelProperty(value = "等级id",dataType = "int",required = true,example = "1")
    private Long levelId;

    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称",dataType = "string",required = true,example = "咖啡新秀")
    private String levelName = "";

    private Integer maxValue;

    private Integer minValue;

    /**
     * 等级图标
     */
    private String imageUrl = "";

    /**
     * 升级提示
     */
    @ApiModelProperty(value = "升级提示",dataType = "string",required = true,example = "再积6颗可升级为咖啡达人")
    private String upgradeTip = "";

    /**
     * 满级标识
     */
    @ApiModelProperty(value = "满级标识",dataType = "boolean",required = true,example = "false")
    private boolean fullLevelFlag = false;

    /**
     * 主卡余额
     */
    @ApiModelProperty(value = "主卡余额",dataType = "double",required = true,example = "0")
    private double primaryCardBalance = 0d;

    /**
     * 可用优惠券数量
     */
    @ApiModelProperty(value = "可用优惠券数量",dataType = "int",required = true,example = "0")
    private int avaliableCouponNum = 0;

    /**
     * vip卡片数量
     */
    @ApiModelProperty(value = "vip卡片数量",dataType = "string",required = true,example = "0")
    private int vipCardNum = 0;

    @ApiModelProperty(value = "二维码编号",dataType = "string")
    private String qrCodeNo = "";

    @ApiModelProperty("商家id")
    private Long merchantId;

}
