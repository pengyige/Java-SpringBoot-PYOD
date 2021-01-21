package top.yigege.dto.modules.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: QueryLabelPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月20日 20:21
 */
@Data
public class QueryLabelPageListDTO {

    @ApiModelProperty("当前页")
    int page = 1;

    @ApiModelProperty("页面大小")
    int pageSize = 10;

    @ApiModelProperty(value = "类型(1-商铺,2-职位,3-学历,4-月收入,5-兴趣，6-偏好)")
    Integer type;
}
