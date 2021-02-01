package top.yigege.dto.modules.coupon;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: QueryUserAvailableCouponPageList
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 13:54
 */
@Data
public class QueryUserAvailableCouponPageListDTO {

    int page = 1;

    int pageSize = 10;

    @NotNull(message = "用户id不能为空")
    Long userId;
}
