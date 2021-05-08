package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.model.ScheduleJobEntity;

import java.util.List;

/**
 * 定时任务
 *
 * @author lwl
 */
@Mapper
public interface ScheduleJobMapper extends BaseMapper<ScheduleJobEntity> {
    List<ScheduleJobEntity> pageList(QueryScheduleJobPageListDTO queryScheduleJobPageListDTO, Page page);
}
