package top.yigege.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.model.ScheduleJobEntity;
import top.yigege.vo.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author lwl
 */
public interface ScheduleJobService  extends IService<ScheduleJobEntity> {



	/**
	 * 立即执行
	 */
	void run(List<Long> jobIds);


	void stop(List<Long> parseLongList);

	/**
	 * 恢复运行
	 */
	void resume(List<Long> jobIds);

    PageBean pageList(QueryScheduleJobPageListDTO queryScheduleJobPageListDTO);

	void deleteQuartzSysJobByIds(List<Long> idsList);

}
