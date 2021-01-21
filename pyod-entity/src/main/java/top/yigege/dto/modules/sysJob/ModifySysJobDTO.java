package top.yigege.dto.modules.sysJob;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: ModifySysJobDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月20日 16:59
 */
@Data
public class ModifySysJobDTO {

    /**
     * 系统任务id
     */
    @ApiModelProperty(value = "任务id",required = true)
    @NotNull(message = "任务id不能为空")
    private Long sysJobId;

    /**
     * 任务名
     */
    @ApiModelProperty(value = "任务名")
    private String name;

    /**
     * 表达式
     */
    @ApiModelProperty(value = "表达式")
    private String cron;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名")
    private String className;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名")
    private String methodName;

    /**
     * 任务状态(1-已创建,2-运行中,3-已停止)
     */
    @ApiModelProperty(value = "任务状态(1-已创建,2-运行中,3-已停止)")
    private Integer status = 1;
}
