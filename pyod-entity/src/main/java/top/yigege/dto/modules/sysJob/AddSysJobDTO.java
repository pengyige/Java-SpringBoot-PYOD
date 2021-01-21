package top.yigege.dto.modules.sysJob;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: AddSysJobDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月20日 16:37
 */
@Data
public class AddSysJobDTO {


    /**
     * 任务名
     */
    @ApiModelProperty(value = "任务名",required = true)
    @NotBlank(message = "任务名不能为空")
    private String name;

    /**
     * 表达式
     */
    @ApiModelProperty(value = "表达式",required = true)
    @NotBlank(message = "表达式不能为空")
    private String cron;

    /**
     * 类名
     */
    @ApiModelProperty(value = "类名",required = true)
    @NotBlank(message = "类名不能为空")
    private String className;

    /**
     * 方法名
     */
    @ApiModelProperty(value = "方法名",required = true)
    @NotBlank(message = "方法名不能为空")
    private String methodName;

    /**
     * 任务状态(1-已创建,2-运行中,3-已停止)
     */
    @ApiModelProperty(value = "任务状态(1-已创建,2-运行中,3-已停止)",required = false)
    private Integer status = 1;
}
