package top.yigege.dto.modules.user;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: UserLoginResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 17:51
 */
@Data
public class UserLoginResDTO {

    /**
     * token
     */
    private String token;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别(0-男,1-女)
     */
    private Integer sex;

    /**
     * 豆豆数量
     */
    private Integer avaliablePeaNum;

    /**
     * 总共获得豆豆数量
     */
    private Integer totalPeaNum;

    /**
     * vip卡片id(主卡)
     */
    private Long vipCardId;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 可用积分
     */
    private Integer avaliableIntegrate;

    /**
     * 累计积分
     */
    private Integer totalIntegrate;

    /**
     * 注册时间
     */
    private Date createTime;


    /**
     * 到期时间
     */
    private Date expireTime;
}
