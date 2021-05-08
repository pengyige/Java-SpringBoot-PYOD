package top.yigege.controller.job;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.scheduleJob.QueryScheduleJobPageListDTO;
import top.yigege.dto.modules.scheduleJobLog.QueryScheduleJobLogPageListDTO;
import top.yigege.job.service.ScheduleJobLogService;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;

import java.util.Map;

/**
 * 定时任务日志
 *
 * @author lwl
 */
@RestController
@RequestMapping("/web/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private ScheduleJobLogService scheduleJobLogService;

    @ApiOperation("查询任务日志分页列表")
    @PostMapping("/queryQuartzSysJobLogPageList")
    public LayuiTableResultBean queryQuartzSysJobPageList(QueryScheduleJobLogPageListDTO queryScheduleJobLogPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = scheduleJobLogService.pageList(queryScheduleJobLogPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }
}
