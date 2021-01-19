package top.yigege.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 系统任务
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Data
@TableName("t_sys_job")
public class SysJob extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 系统任务id
     */
    private Long sysJobId;

    /**
     * 任务名
     */
    private String name;

    /**
     * 表达式
     */
    private String cron;

    /**
     * 类名
     */
    private String className;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 任务状态(1-已创建,2-运行中,3-已停止)
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;


}
