package top.yigege.dto.modules.city;

import lombok.Data;
import top.yigege.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: QueryCityDataResDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月15日 15:52
 */
@Data
public class QueryCityDataResDTO {

    List<City> hotCityList = new ArrayList<>();

    List<City> cityList = new ArrayList<>();
}
