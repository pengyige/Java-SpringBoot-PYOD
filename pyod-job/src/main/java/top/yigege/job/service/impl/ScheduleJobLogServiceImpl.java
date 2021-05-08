package top.yigege.job.service.impl;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.dao.ScheduleJobLogMapper;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.dto.modules.scheduleJobLog.QueryScheduleJobLogPageListDTO;
import top.yigege.job.service.ScheduleJobLogService;
import top.yigege.model.ScheduleJobEntity;
import top.yigege.model.ScheduleJobLogEntity;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements ScheduleJobLogService {

    @Resource
    ScheduleJobLogMapper scheduleJobLogMapper;

    @Override
    public PageBean pageList(QueryScheduleJobLogPageListDTO queryScheduleJobLogPageListDTO) {
        Page page = new Page(queryScheduleJobLogPageListDTO.getPage(),
                queryScheduleJobLogPageListDTO.getPageSize());
        List<ScheduleJobLogEntity> scheduleJobLogEntityList = scheduleJobLogMapper.pageList(
                queryScheduleJobLogPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, scheduleJobLogEntityList);
    }

}
