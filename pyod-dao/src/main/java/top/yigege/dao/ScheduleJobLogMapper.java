package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import top.yigege.dto.modules.scheduleJobLog.QueryScheduleJobLogPageListDTO;
import top.yigege.model.ScheduleJobLogEntity;

import java.util.List;

/**
 * 定时任务日志
 *
 * @author lwl
 */
@Mapper
public interface ScheduleJobLogMapper extends BaseMapper<ScheduleJobLogEntity> {

    List<ScheduleJobLogEntity> pageList(QueryScheduleJobLogPageListDTO queryScheduleJobLogPageListDTO, Page page);
}
