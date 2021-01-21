package top.yigege.dto.modules.label;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddLabelDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月20日 20:15
 */
@Data
public class ModifyLabelDTO {

    @ApiModelProperty(value = "标签id",required = true)
    @NotNull(message = "标签id不能为空")
    private Integer labelId;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 类型(1-商铺,2-职位,3-学历,4-月收入,5-兴趣，6-偏好)
     */
    @ApiModelProperty(value = "类型")
    private Integer type;
}
