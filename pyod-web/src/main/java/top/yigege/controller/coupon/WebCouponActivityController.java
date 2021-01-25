package top.yigege.controller.coupon;


import cn.hutool.core.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ActivityStatusEnum;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.coupon.AddCouponActivityDTO;
import top.yigege.dto.modules.coupon.ModifyCouponActivityDTO;
import top.yigege.dto.modules.coupon.QueryCouponActivityPageListDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.CouponActivity;
import top.yigege.service.ICouponActivityService;
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
@Api(tags = "优惠券活动(WEB)")
@RestController
@RequestMapping("/web/couponActivity")
@Validated
public class WebCouponActivityController {

    @Autowired
    ICouponActivityService iCouponActivityService;

    @ApiOperation("添加活动")
    @PostMapping("/addCouponActivity")
    public ResultBean addCouponActivity(@Valid AddCouponActivityDTO addCouponActivityDTO){
        CouponActivity couponActivity = new CouponActivity();
        BeanUtil.copyProperties(addCouponActivityDTO, couponActivity);


        ActivityTypeEnum typeEnum = null;
        for (ActivityTypeEnum activityTypeEnum : ActivityTypeEnum.values()) {
            if (addCouponActivityDTO.getStatus().equals(activityTypeEnum.getCode())) {
                typeEnum = activityTypeEnum;
                break;
            }
        }

        if (iCouponActivityService.isExist(typeEnum)) {
            throw new BusinessException(ResultCodeEnum.ACTIVITY_EXIST);
        };

        return ApiResultUtil.success(iCouponActivityService.save(couponActivity));
    };

    @ApiOperation("修改活动")
    @PostMapping("/modifyCouponActivity")
    public ResultBean modifyCouponActivity(@Valid ModifyCouponActivityDTO modifyCouponActivityDTO) {
        CouponActivity CouponActivity = new CouponActivity();
        BeanUtil.copyProperties(modifyCouponActivityDTO, CouponActivity);
        CouponActivity dbCouponActivity = iCouponActivityService.getById(modifyCouponActivityDTO.getCouponActivityId());

        if (null != modifyCouponActivityDTO.getStatus()
        && modifyCouponActivityDTO.getStatus().equals(ActivityStatusEnum.NORMAL)
        && dbCouponActivity.getStatus().equals(ActivityStatusEnum.STOP)) {
            ActivityTypeEnum typeEnum = null;
            for (ActivityTypeEnum activityTypeEnum : ActivityTypeEnum.values()) {
                if (modifyCouponActivityDTO.getStatus().equals(activityTypeEnum.getCode())) {
                    typeEnum = activityTypeEnum;
                    break;
                }
            }
            if (iCouponActivityService.isExist(typeEnum)) {
                throw new BusinessException(ResultCodeEnum.ACTIVITY_EXIST);
            };
        }


        return ApiResultUtil.success(iCouponActivityService.updateById(CouponActivity));
    }

    @ApiOperation("删除活动")
    @PostMapping("/deleteCouponActivityByIds")
    public ResultBean deleteCouponActivityByIds(@NotBlank(message = "活动id不能为空") String couponActivityIds) {
        return ApiResultUtil.success(iCouponActivityService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(couponActivityIds))));
    }

    @ApiOperation("查询活动分页列表")
    @PostMapping("/queryCouponActivityPageList")
    public LayuiTableResultBean queryCouponActivityPageList(QueryCouponActivityPageListDTO queryCouponActivityPageListDTO) {

        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iCouponActivityService.queryCouponActivityPageList(queryCouponActivityPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询活动详情")
    @PostMapping("/queryCouponActivityDetail")
    public ResultBean queryCouponActivityDetail(@NotNull(message = "活动id不能为空") Long couponActivityId) {
        return ApiResultUtil.success(iCouponActivityService.getById(couponActivityId));
    }

}
