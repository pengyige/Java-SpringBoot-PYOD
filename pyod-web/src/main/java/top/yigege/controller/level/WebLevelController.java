package top.yigege.controller.level;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.level.AddLevelDTO;
import top.yigege.dto.modules.level.ModifyLevelDTO;
import top.yigege.dto.modules.level.QueryLevelPageListDTO;
import top.yigege.model.Label;
import top.yigege.model.Level;
import top.yigege.service.ILevelService;
import top.yigege.service.ILevelWelfareService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 系统任务 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "等级(WEB)")
@RestController
@RequestMapping("/web/level")
@Validated
public class WebLevelController {

    @Autowired
    ILevelService iLevelService;

    @Autowired
    ILevelWelfareService iLevelWelfareService;

    @ApiOperation("添加等级")
    @PostMapping("/addLevel")
    public ResultBean addLevel(@Valid AddLevelDTO addLevelDTO){
        Level level = new Level();
        BeanUtil.copyProperties(addLevelDTO, level);
        level.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        iLevelService.save(level);
        iLevelWelfareService.bindLevelWelfare(level.getLevelId(),addLevelDTO.getWelfareIds());

        return ApiResultUtil.success();
    };

    @ApiOperation("修改等级")
    @PostMapping("/modifyLevel")
    public ResultBean modifyLevel(@Valid ModifyLevelDTO modifyLevelDTO) {
        Level Level = new Level();
        BeanUtil.copyProperties(modifyLevelDTO, Level);
        iLevelWelfareService.bindLevelWelfare(Level.getLevelId(),modifyLevelDTO.getWelfareIds());

        return ApiResultUtil.success(iLevelService.updateById(Level));
    }

    @ApiOperation("删除等级")
    @PostMapping("/deleteLevelByIds")
    public ResultBean deleteLevelByIds(@NotBlank(message = "等级id不能为空") String levelIds) {
        List<Integer> list = Utils.parseIntegersList(Utils.splitStringToList(levelIds));
        for (Integer levelId : list) {
            iLevelWelfareService.deleteLevelWelfare(Long.valueOf(levelId));
        }

        return ApiResultUtil.success(iLevelService.removeByIds(list));
    }

    @ApiOperation("查询等级分页列表")
    @PostMapping("/queryLevelPageList")
    public LayuiTableResultBean queryLevelPageList(QueryLevelPageListDTO queryLevelPageListDTO) {
        queryLevelPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iLevelService.queryLevelPageList(queryLevelPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询等级详情")
    @PostMapping("/queryLevelDetail")
    public ResultBean queryLevelDetail(@NotNull(message = "等级id不能为空") Long levelId) {
        Level level = iLevelService.getById(levelId);
        level.setLevelWelfareList(iLevelWelfareService.queryLevelWelfareByLevelId(levelId));
        return ApiResultUtil.success(level);
    }

    @ApiOperation("查询所有等级列表")
    @PostMapping("/queryAllLevelList")
    public ResultBean queryAllLevelList() {
        return ApiResultUtil.success(iLevelService.queryLevelByMerchantId(Long.valueOf(SessionUtil.getUser().getUserId())));
    }

}
