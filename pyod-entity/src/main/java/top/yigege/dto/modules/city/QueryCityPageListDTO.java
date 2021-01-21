package top.yigege.dto.modules.city;

import lombok.Data;

/**
 * @ClassName: QueryCityPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 15:59
 */
@Data
public class QueryCityPageListDTO {

    int page = 1;

    int pageSize = 10;
}
