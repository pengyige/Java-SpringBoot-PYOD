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
public class QueryShopDetailDTO {

    @ApiModelProperty(value = "商店id",required = true)
    @NotNull(message = "商店id不能为空")
    Long shopId;

    @ApiModelProperty(value = "经度")
    Double longitude;

    @ApiModelProperty(value = "纬度")
    Double latitude;

}
