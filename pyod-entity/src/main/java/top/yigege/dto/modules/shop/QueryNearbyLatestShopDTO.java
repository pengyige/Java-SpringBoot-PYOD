package top.yigege.dto.modules.shop;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;


/**
 * @ClassName: QueryNearbyLatestShopDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月14日 13:50
 */
@Data
public class QueryNearbyLatestShopDTO {

    @ApiModelProperty(value = "经度",required = true)
    @NotNull(message = "经度不能为空")
    Double longitude;

    @ApiModelProperty(value = "纬度",required = true)
    @NotNull(message = "纬度不能为空")
    Double latitude;

    @ApiModelProperty(value = "城市id")
    Long cityId;
}
