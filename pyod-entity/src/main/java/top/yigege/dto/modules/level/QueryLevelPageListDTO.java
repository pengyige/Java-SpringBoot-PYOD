package top.yigege.dto.modules.level;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryLevelPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 13:26
 */
@Data
public class QueryLevelPageListDTO {

    @ApiModelProperty("当前页")
    int page = 1;

    @ApiModelProperty("页面大小")
    int pageSize = 10;
}
