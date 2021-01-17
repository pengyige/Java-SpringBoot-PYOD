package top.yigege.dto.modules.userVipCard;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName: QueryUserVipCardInfoResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月17日 20:14
 */
@Data
public class QueryUserVipCardInfoResDTO {

    Long vipCardId;
    String vipCardNo;
    String vipCardName;
    String imageUrl;
    Double balance;
    Integer primaryFlag;
    Date createTime;
}
