package top.yigege.controller.job;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.scheduleJob.AddScheduleJobDTO;
import top.yigege.dto.modules.scheduleJob.ModifyScheduleJobDTO;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.dto.modules.sysJob.AddSysJobDTO;
import top.yigege.dto.modules.sysJob.ModifySysJobDTO;
import top.yigege.dto.modules.sysJob.QuerySysJobPageListDTO;
import top.yigege.job.service.ScheduleJobService;
import top.yigege.job.util.ScheduleUtils;
import top.yigege.model.ScheduleJobEntity;
import top.yigege.model.SysJob;
import top.yigege.service.ISysJobService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiFileUploadResultBean;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 定时任务
 *
 * @author lwl
 */
@Slf4j
@RestController
@RequestMapping("/web/schedule")
public class ScheduleJobController {

  /*  *//**
     * 定时任务列表
     *//*
    @RequestMapping("/list")
    public LayuiFileUploadResultBean list(@RequestParam Map<String, Object> params) {
        scheduleJobService.queryPage(params);
        return
    }

    *//**
     * 保存修改定时任务
     *//*
    @SysLog("保存修改定时任务")
    @RequestMapping("/saveOrUpdate")
    public R saveOrUpdate(@RequestBody ScheduleJobEntity scheduleJob) {
        scheduleJobService.saveOrUpdate(scheduleJob);
        return R.ok();
    }

    *//**
     * 删除定时任务
     *//*
    @SysLog("删除定时任务")
    @RequestMapping("/delete")
    @PreAuthorize("hasAnyRole('sys:schedule:delete')")
    public R delete(@RequestBody Long[] jobIds) {
        log.debug("delete jobIds:{}", jobIds);
        scheduleJobService.deleteBatch(jobIds);

        return R.ok();
    }

    *//**
     * 立即执行任务
     *//*
    @SysLog("立即执行任务")
    @RequestMapping("/run")
    @PreAuthorize("hasAnyRole('sys:schedule:run')")
    public R run(@RequestBody Long[] jobIds) {
        log.debug("run jobIds:{}", jobIds);
        scheduleJobService.run(jobIds);

        return R.ok();
    }

    *//**
     * 暂停定时任务
     *//*
    @SysLog("暂停定时任务")
    @RequestMapping("/pause")
    @PreAuthorize("hasAnyRole('sys:schedule:pause')")
    public R pause(@RequestBody Long[] jobIds) {
        log.debug("pause jobIds:{}", jobIds);
        scheduleJobService.pause(jobIds);

        return R.ok();
    }

    *//**
     * 恢复定时任务
     *//*
    @SysLog("恢复定时任务")
    @RequestMapping("/resume")
    @PreAuthorize("hasAnyRole('sys:schedule:resume')")
    public R resume(@RequestBody Long[] jobIds) {
        log.debug("resume jobIds:{}", jobIds);
        scheduleJobService.resume(jobIds);

        return R.ok();
    }*/

    @Autowired
    ScheduleJobService scheduleJobService;

    @Resource
    Scheduler scheduler;

    @ApiOperation("添加任务")
    @PostMapping("/addQuartzSysJob")
    public ResultBean addQuartzSysJob(@Valid AddScheduleJobDTO addScheduleJobDTO){
        ScheduleJobEntity scheduleJobEntity = new ScheduleJobEntity();
        BeanUtil.copyProperties(addScheduleJobDTO, scheduleJobEntity);
        scheduleJobService.save(scheduleJobEntity);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJobEntity);
        return ApiResultUtil.success();
    };

    @ApiOperation("修改任务")
    @PostMapping("/modifyQuartzSysJob")
    public ResultBean modifyQuartzSysJob(@Valid ModifyScheduleJobDTO modifyScheduleJobDTO) {
        ScheduleJobEntity scheduleJobEntity = new ScheduleJobEntity();
        BeanUtil.copyProperties(modifyScheduleJobDTO, scheduleJobEntity);
        scheduleJobService.updateById(scheduleJobEntity);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJobEntity);
        return ApiResultUtil.success();
    }

    @ApiOperation("删除任务")
    @PostMapping("/deleteQuartzSysJobByIds")
    public ResultBean deleteQuartzSysJobByIds(@NotBlank(message = "任务id不能为空") String ids) {
        List<Long> idsList = Utils.parseLongList(Utils.splitStringToList(ids));
        scheduleJobService.deleteQuartzSysJobByIds(idsList);
        return ApiResultUtil.success();

    }

    @ApiOperation("查询任务分页列表")
    @PostMapping("/queryQuartzSysJobPageList")
    public LayuiTableResultBean queryQuartzSysJobPageList(QueryScheduleJobPageListDTO queryScheduleJobPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = scheduleJobService.pageList(queryScheduleJobPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询系统任务详情")
    @PostMapping("/queryQuartzSysJobDetail")
    public ResultBean queryQuartzSysJobDetail(@NotNull(message = "任务id不能为空") Long id) {
        return ApiResultUtil.success(scheduleJobService.getById(id));
    }

    @ApiOperation("立即运行")
    @PostMapping("/run")
    public ResultBean run(@NotBlank(message = "任务id不能为空") String ids) {
        log.debug("run jobIds:{}", ids);
        scheduleJobService.run(Utils.parseLongList(Utils.splitStringToList(ids)));

        return ApiResultUtil.success();
    }


    @ApiOperation("暂停")
    @RequestMapping("/stop")
    public ResultBean stop(@NotBlank(message = "任务id不能为空") String ids) {
        log.debug("run jobIds:{}", ids);
        scheduleJobService.stop(Utils.parseLongList(Utils.splitStringToList(ids)));

        return ApiResultUtil.success();
    }


    @ApiOperation("恢复")
    @RequestMapping("/resume")
    public ResultBean resume(@NotBlank(message = "任务id不能为空") String ids) {
        log.debug("run jobIds:{}", ids);
        scheduleJobService.resume(Utils.parseLongList(Utils.splitStringToList(ids)));
        return ApiResultUtil.success();
    }

}
