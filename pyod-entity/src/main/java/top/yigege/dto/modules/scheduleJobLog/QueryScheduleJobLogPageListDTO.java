package top.yigege.dto.modules.scheduleJobLog;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryScheduleJobPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月08日 10:37
 */
@Data
public class QueryScheduleJobLogPageListDTO {

    @ApiModelProperty("当前页")
    int page = 1;

    @ApiModelProperty("分页大小")
    int pageSize = 10;
}
