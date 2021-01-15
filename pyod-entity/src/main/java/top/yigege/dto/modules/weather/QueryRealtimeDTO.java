package top.yigege.dto.modules.weather;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryRealtimeDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:10
 */
@Data
public class QueryRealtimeDTO {

    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    String cityId;

    /**
     * 经度
     */
    @ApiModelProperty("经度")
    Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty("纬度")
    Double latitude;

    /**
     * 城市名
     */
    @ApiModelProperty("城市名")
    String cityName;
}
