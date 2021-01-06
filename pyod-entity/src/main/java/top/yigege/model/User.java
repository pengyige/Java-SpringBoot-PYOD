package top.yigege.model;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2021-01-05
 */
@Data
@TableName("t_user")
public class User extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

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
     * 可用积分
     */
    private Integer avaliableIntegrate;

    /**
     * 累计积分
     */
    private Integer totalIntegrate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 到期时间
     */
    private Date expireTime;

}
