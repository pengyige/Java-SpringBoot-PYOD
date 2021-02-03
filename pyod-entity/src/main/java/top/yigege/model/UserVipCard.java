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
 * 
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Data
@TableName("t_user_vip_card")
public class UserVipCard extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 用户vip卡片id
     */
    @TableId(value = "user_vip_card_id", type = IdType.AUTO)
    private Long userVipCardId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * vip卡片id
     */
    private Long vipCardId;

    /**
     * 卡片编号
     */
    private String cardNo;

    /**
     * 卡密码
     */
    private String cardPsw;

    /**
     * 会员卡生日
     */
    private Date birthday;

    /**
     * 主卡标识(0-非主卡,1-主卡)
     */
    private Integer primaryFlag;

    /**
     * 余额
     */
    private Double balance;

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
