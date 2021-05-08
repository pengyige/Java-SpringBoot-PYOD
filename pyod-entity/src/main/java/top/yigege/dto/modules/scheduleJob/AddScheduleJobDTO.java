package top.yigege.dto.modules.scheduleJob;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * @ClassName: AddScheduleJobDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月08日 10:37
 */
@Data
public class AddScheduleJobDTO {

    /**
     * spring bean名称
     */
    @NotBlank(message="bean名称不能为空")
    private String beanName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
    @NotBlank(message="cron表达式不能为空")
    private String cronExpression;

    /**
     * 任务状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
