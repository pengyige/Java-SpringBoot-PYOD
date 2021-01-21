package top.yigege.controller;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;

import top.yigege.dto.modules.sysJob.AddSysJobDTO;
import top.yigege.dto.modules.sysJob.ModifySysJobDTO;
import top.yigege.dto.modules.sysJob.QuerySysJobPageListDTO;
import top.yigege.model.SysJob;
import top.yigege.model.SysJob;
import top.yigege.service.ISysJobService;
import top.yigege.service.ISysJobService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统任务 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "系统任务(WEB)")
@RestController
@RequestMapping("/web/sysJob")
@Validated
public class SysJobController {

    @Autowired
    ISysJobService iSysJobService;

    @ApiOperation("添加任务")
    @PostMapping("/addSysJob")
    public ResultBean addSysJob(@Valid AddSysJobDTO addSysJobDTO){
        SysJob sysJob = new SysJob();
        BeanUtil.copyProperties(addSysJobDTO, sysJob);

        return ApiResultUtil.success(iSysJobService.save(sysJob));
    };

    @ApiOperation("修改任务")
    @PostMapping("/modifySysJob")
    public ResultBean modifySysJob(@Valid ModifySysJobDTO modifySysJobDTO) {
        SysJob SysJob = new SysJob();
        BeanUtil.copyProperties(modifySysJobDTO, SysJob);

        return ApiResultUtil.success(iSysJobService.updateById(SysJob));
    }

    @ApiOperation("删除任务")
    @PostMapping("/deleteSysJobByIds")
    public ResultBean deleteSysJobByIds(@NotBlank(message = "任务id不能为空") String sysJobIds) {
        return ApiResultUtil.success(iSysJobService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(sysJobIds))));
    }

    @ApiOperation("查询任务分页列表")
    @PostMapping("/querySysJobPageList")
    public LayuiTableResultBean querySysJobPageList(QuerySysJobPageListDTO querySysJobPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iSysJobService.querySysJobPageList(querySysJobPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询系统任务详情")
    @PostMapping("/querySysJobDetail")
    public ResultBean querySysJobDetail(@NotNull(message = "任务id不能为空") Long sysJobId) {
        return ApiResultUtil.success(iSysJobService.getById(sysJobId));
    }

}
