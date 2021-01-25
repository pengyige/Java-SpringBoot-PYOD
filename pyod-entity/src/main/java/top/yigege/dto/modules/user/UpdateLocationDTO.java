package top.yigege.dto.modules.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: UpdateLocationDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月23日 15:34
 */
@Data
public class UpdateLocationDTO {

    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    Double longitude;

    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    Double latitude;

    Long userId;

}
