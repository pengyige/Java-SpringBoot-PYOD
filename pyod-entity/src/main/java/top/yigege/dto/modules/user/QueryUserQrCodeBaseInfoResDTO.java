package top.yigege.dto.modules.user;

import lombok.Data;

/**
 * @ClassName: QueryUserQrCodeBaseInfoDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月10日 10:23
 */
@Data
public class QueryUserQrCodeBaseInfoResDTO {

    /**
     * 主卡ID
     */
    private Long primaryVipCardId;


    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 可用优惠券数量
     */
    private int availableCouponNum;




}
