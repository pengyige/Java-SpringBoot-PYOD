package top.yigege.dto.modules.level;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddLevelDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 13:17
 */
@Data
public class AddLevelDTO {

    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称",required = true)
    @NotBlank(message = "等级名不能为空")
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty(value = "等级logo",required = true)
    @NotBlank(message = "等级logo不能为空")
    private String imageUrl;

    /**
     * 达成条件最小值
     */
    @ApiModelProperty(value = "最小值",required = true)
    @NotNull(message = "最小值不能为空")
    private Integer minValue;

    /**
     * 达成条件最大值
     */
    @ApiModelProperty(value = "最大值",required = true)
    @NotNull(message = "最大值不能为空")
    private Integer maxValue;


    /**
     * 福利ids
     */
    String welfareIds;
}
