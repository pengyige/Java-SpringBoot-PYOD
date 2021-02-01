package top.yigege.dto.modules.level;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: ModifyLevelDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 13:17
 */
@Data
public class ModifyLevelDTO {

    @ApiModelProperty(value = "等级id",required = true)
    @NotNull(message = "等级id不能为空")
    private Long levelId;

    /**
     * 等级名称
     */
    @ApiModelProperty(value = "等级名称")
    private String name;

    /**
     * 图片
     */
    @ApiModelProperty(value = "等级logo")
    private String imageUrl;

    /**
     * 达成条件最小值
     */
    @ApiModelProperty(value = "最小值")
    private Integer minValue;

    /**
     * 达成条件最大值
     */
    @ApiModelProperty(value = "最大值")
    private Integer maxValue;

    /**
     * 福利ids
     */
    String welfareIds;
}
