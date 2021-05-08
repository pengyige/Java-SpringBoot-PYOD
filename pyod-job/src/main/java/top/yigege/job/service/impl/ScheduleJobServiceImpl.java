package top.yigege.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.constant.ScheduleStatus;
import top.yigege.dao.ScheduleJobMapper;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.job.service.ScheduleJobService;
import top.yigege.job.util.ScheduleUtils;
import top.yigege.model.CouponActivityBirthday;
import top.yigege.model.ScheduleJobEntity;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;


import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJobEntity> implements ScheduleJobService {

    @Resource
    private Scheduler scheduler;

    @Resource
    ScheduleJobMapper scheduleJobMapper;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJobEntity> scheduleJobList = this.list();
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

   /* @Override
    public PageUtil queryPage(Map<String, Object> params) {
        String beanName = (String) params.get("beanName");
        LambdaQueryWrapper<ScheduleJobEntity> qw = new LambdaQueryWrapper<>();
        qw.like(StringUtils.isNotBlank(beanName), ScheduleJobEntity::getBeanName, beanName);
        IPage<ScheduleJobEntity> page = this.page(new Query<ScheduleJobEntity>().getPage(params), qw);
        return new PageUtil(page);
    }*/

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(ScheduleJobEntity entity) {
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
            entity.setStatus(ScheduleStatus.NORMAL.getValue());
            this.save(entity);
            ScheduleUtils.createScheduleJob(scheduler, entity);
        } else {
            ScheduleUtils.updateScheduleJob(scheduler, entity);
            this.updateById(entity);
        }
        return true;
    }


    private void updateBatch(List<Long> jobIds, int status) {
        List<ScheduleJobEntity> list = new ArrayList<>();
        for (Long jobId : jobIds) {
            ScheduleJobEntity job = new ScheduleJobEntity();
            job.setId(jobId);
            job.setStatus(status);
            list.add(job);
        }
        if (!list.isEmpty()) {
            this.updateBatchById(list);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(List<Long> jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    public void stop(List<Long> parseLongList) {
        for (Long jobId : parseLongList) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }

        updateBatch(parseLongList, ScheduleStatus.PAUSE.getValue());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(List<Long>jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }

        updateBatch(jobIds, ScheduleStatus.NORMAL.getValue());
    }

    @Override
    public PageBean pageList(QueryScheduleJobPageListDTO queryScheduleJobPageListDTO) {
        Page page = new Page(queryScheduleJobPageListDTO.getPage(),
                queryScheduleJobPageListDTO.getPageSize());
        List<ScheduleJobEntity> scheduleJobEntityList = scheduleJobMapper.pageList(
                queryScheduleJobPageListDTO,
                page
        );
        return PageUtil.getPageBean(page, scheduleJobEntityList);
    }

    @Transactional
    @Override
    public void deleteQuartzSysJobByIds(List<Long> idsList) {
        for (Long jobId : idsList) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        //删除数据
        this.removeByIds(idsList);
    }

}
