package top.yigege.dto.modules.welfare;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddWelfareDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 20:42
 */
@Data
public class AddWelfareDTO {

    /**
     * 福利名称
     */
    @NotBlank(message = "福利名称不能为空")
    private String name;

    /**
     * 福利图片
     */
    @NotBlank(message = "福利图片不能为空")
    private String imageUrl;

    /**
     * 描述
     */
    @NotBlank(message = "福利描述不能为空")
    private String desc;

}
