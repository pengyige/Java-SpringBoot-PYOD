package top.yigege.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.scheduleJobLog.QueryScheduleJobLogPageListDTO;
import top.yigege.model.ScheduleJobLogEntity;
import top.yigege.vo.PageBean;

/**
 * 定时任务日志
 *
 * @author lwl
 */
public interface ScheduleJobLogService extends IService<ScheduleJobLogEntity> {

     PageBean pageList(QueryScheduleJobLogPageListDTO queryScheduleJobLogPageListDTO);


}
