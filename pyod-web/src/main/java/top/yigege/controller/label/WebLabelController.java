package top.yigege.controller.label;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.label.AddLabelDTO;
import top.yigege.dto.modules.label.ModifyLabelDTO;
import top.yigege.dto.modules.label.QueryLabelPageListDTO;
import top.yigege.model.Label;
import top.yigege.service.ILabelService;
import top.yigege.service.ILabelService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 系统任务 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "标签(WEB)")
@RestController
@RequestMapping("/web/label")
@Validated
public class WebLabelController {

    @Autowired
    ILabelService iLabelService;

    @ApiOperation("添加标签")
    @PostMapping("/addLabel")
    public ResultBean addLabel(@Valid AddLabelDTO addLabelDTO){
        Label label = new Label();
        BeanUtil.copyProperties(addLabelDTO, label);
        return ApiResultUtil.success(iLabelService.save(label));
    };

    @ApiOperation("修改标签")
    @PostMapping("/modifyLabel")
    public ResultBean modifyLabel(@Valid ModifyLabelDTO modifyLabelDTO) {
        Label Label = new Label();
        BeanUtil.copyProperties(modifyLabelDTO, Label);

        return ApiResultUtil.success(iLabelService.updateById(Label));
    }

    @ApiOperation("删除标签")
    @PostMapping("/deleteLabelByIds")
    public ResultBean deleteLabelByIds(@NotBlank(message = "标签id不能为空") String labelIds) {
        return ApiResultUtil.success(iLabelService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(labelIds))));
    }

    @ApiOperation("查询标签分页列表")
    @PostMapping("/queryLabelPageList")
    public LayuiTableResultBean queryLabelPageList(QueryLabelPageListDTO queryLabelPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iLabelService.queryLabelPageList(queryLabelPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询标签详情")
    @PostMapping("/queryLabelDetail")
    public ResultBean queryLabelDetail(@NotNull(message = "标签id不能为空") Long labelId) {
        return ApiResultUtil.success(iLabelService.getById(labelId));
    }

}
