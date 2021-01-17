package top.yigege.controller.api.vipCard;

import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.dto.modules.userVipCard.BindUserVipCardDTO;
import top.yigege.service.IUserVipCardService;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

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
    public ResultBean bindUserVipCard(@Validated BindUserVipCardDTO bindUserVipCardDTO, @RequestAttribute Long userId){
        bindUserVipCardDTO.setUserId(userId);
        iUserVipCardService.bindVipCard(bindUserVipCardDTO);
        return ApiResultUtil.success();
    }


}
