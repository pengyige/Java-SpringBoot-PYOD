package top.yigege.controller.welfare;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.welfare.AddWelfareDTO;
import top.yigege.dto.modules.welfare.ModifyWelfareDTO;
import top.yigege.dto.modules.welfare.QueryWelfarePageListDTO;
import top.yigege.model.Welfare;
import top.yigege.service.IWelfareService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
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
@Api(tags = "福利(WEB)")
@RestController
@RequestMapping("/web/welfare")
@Validated
public class WebWelfareController {

    @Autowired
    IWelfareService iWelfareService;

    @ApiOperation("添加福利")
    @PostMapping("/addWelfare")
    public ResultBean addWelfare(@Valid AddWelfareDTO addWelfareDTO){
        Welfare welfare = new Welfare();
        BeanUtil.copyProperties(addWelfareDTO, welfare);
        welfare.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        return ApiResultUtil.success(iWelfareService.save(welfare));
    };

    @ApiOperation("修改福利")
    @PostMapping("/modifyWelfare")
    public ResultBean modifyWelfare(@Valid ModifyWelfareDTO modifyWelfareDTO) {
        Welfare Welfare = new Welfare();
        BeanUtil.copyProperties(modifyWelfareDTO, Welfare);

        return ApiResultUtil.success(iWelfareService.updateById(Welfare));
    }

    @ApiOperation("删除福利")
    @PostMapping("/deleteWelfareByIds")
    public ResultBean deleteWelfareByIds(@NotBlank(message = "福利id不能为空") String welfareIds) {
        return ApiResultUtil.success(iWelfareService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(welfareIds))));
    }

    @ApiOperation("查询福利分页列表")
    @PostMapping("/queryWelfarePageList")
    public LayuiTableResultBean queryWelfarePageList(QueryWelfarePageListDTO queryWelfarePageListDTO) {
        queryWelfarePageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iWelfareService.queryWelfarePageList(queryWelfarePageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询福利详情")
    @PostMapping("/queryWelfareDetail")
    public ResultBean queryWelfareDetail(@NotNull(message = "福利id不能为空") Long welfareId) {
        return ApiResultUtil.success(iWelfareService.getById(welfareId));
    }

}
