package top.yigege.controller.api.vipCard;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.dto.modules.userVipCard.ModiftVipCardBirthdayDTO;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: ApiVipCardController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月13日 21:27
 */
@RestController
@RequestMapping("/api/userVipCard")
@Validated
@Api(tags = "API-VIP卡片相关请求")
public class ApiUserVipCardController {

    @Autowired
    IUserVipCardService iUserVipCardService;

    @ApiOperation("绑定VIP卡片")
    @PostMapping("/bindUserVipCard")
    public ResultBean bindUserVipCard(@Valid BindUserVipCardDTO bindUserVipCardDTO, @RequestAttribute Long userId){
        bindUserVipCardDTO.setUserId(userId);
        iUserVipCardService.bindVipCard(bindUserVipCardDTO);
        return ApiResultUtil.success();
    }

    @ApiOperation("解除绑定vip卡片")
    @ApiImplicitParams({@ApiImplicitParam(name = "vipCardId", value = "用户vip卡片id",paramType = "query",dataType = "int",required = true)})
    @PostMapping("/unBindUserVipCard")
    public ResultBean unBindUserVipCard(@NotNull(message = "卡片id不能为空") Long vipCardId,@RequestAttribute Long userId) {
        iUserVipCardService.unBindVipCard(vipCardId,userId);
        return ApiResultUtil.success();
    }

    @ApiOperation("设置为主卡")
    @ApiImplicitParams({@ApiImplicitParam(name = "vipCardId", value = "用户vip卡片id",paramType = "query",dataType = "int",required = true)})
    @PostMapping("/setPrimaryCard")
    public ResultBean setPrimaryCard(@NotNull(message = "卡片id不能为空") Long vipCardId,@RequestAttribute Long userId) {
        iUserVipCardService.setPrimaryCard(vipCardId,userId);
        return ApiResultUtil.success();
    }

    @ApiOperation("修改会员卡生日")
    @PostMapping("/modifyVipCardBirthday")
    public ResultBean modifyVipCardBirthday(@Valid ModiftVipCardBirthdayDTO modiftVipCardBirthdayDTO, @RequestAttribute Long userId) {
        modiftVipCardBirthdayDTO.setUserId(userId);
        iUserVipCardService.modifyVipCardBirthday(modiftVipCardBirthdayDTO);
        return ApiResultUtil.success();
    }

    @ApiOperation(value = "查询用户所有vip卡片信息")
    @PostMapping("/queryUserVipCardList")
    public ResultBean queryUserVipCardList(@RequestAttribute Long userId) {
        return ApiResultUtil.success(iUserVipCardService.queryUserVipCardList(userId));
    }

    @ApiOperation("查询用户会员卡详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "vipCardId", value = "用户vip卡片id",paramType = "query",dataType = "int",required = true)})
    @PostMapping("/queryUserVipCardDetail")
    public ResultBean queryUserVipCardDetail(@NotNull(message = "用户会员卡id不能为空") Long vipCardId) {
        return ApiResultUtil.success(iUserVipCardService.queryUserVipCardDetail(vipCardId));
    }


}
