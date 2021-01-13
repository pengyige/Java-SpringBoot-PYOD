package top.yigege.dto.modules.weather;

import lombok.Data;

/**
 * @ClassName: QueryRealtimeDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月08日 14:10
 */
@Data
public class QuerySevenDTO {

    /**
     * 经度
     */
    Double longitude;

    /**
     * 纬度
     */
    Double latitude;

    /**
     * 城市名
     */
    String cityName;
}
