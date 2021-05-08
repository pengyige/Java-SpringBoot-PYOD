package top.yigege.dto.modules.scheduleJob;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: AddScheduleJobDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年05月08日 10:37
 */
@Data
public class ModifyScheduleJobDTO {

    /**
     * 任务id
     */
    @NotNull(message = "id不能为空")
    private Long id;

    /**
     * spring bean名称
     */
    private String beanName;

    /**
     * 参数
     */
    private String params;

    /**
     * cron表达式
     */
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
