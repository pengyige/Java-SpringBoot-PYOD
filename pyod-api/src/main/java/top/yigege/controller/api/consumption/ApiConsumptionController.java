package top.yigege.controller.api.consumption;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.ResultBean;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: ApiConsumptionController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月23日 12:19
 */
@RestController
@RequestMapping("/api/consumption")
@Validated
@Api(tags = "API-消费模块")
public class ApiConsumptionController {

    @ApiOperation(value = "查询消费记录详情")
    @ApiImplicitParams({@ApiImplicitParam(name = "consumptionRecordId", value = "消费记录id",paramType = "query",dataType = "int",required = true)})
    @PostMapping("/queryConsumptionRecordDetail")
    public ResultBean queryConsumptionRecordDetail(@NotNull(message = "消费记录id不能为空") Long consumptionRecordId) {
        //TODO 待完善
        return ApiResultUtil.success();
    }
}
