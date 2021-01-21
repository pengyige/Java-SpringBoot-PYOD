package top.yigege.dto.modules.sysJob;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QuerySysJobPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月20日 17:17
 */
@Data
public class QuerySysJobPageListDTO {

    @ApiModelProperty("当前页")
    int page = 1;

    @ApiModelProperty("分页大小")
    int pageSize = 10;
}
