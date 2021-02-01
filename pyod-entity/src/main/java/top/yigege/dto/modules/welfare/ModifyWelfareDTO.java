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
public class ModifyWelfareDTO {

    @NotNull(message = "福利id不能为空")
    private String welfareId;

    /**
     * 福利名称
     */
    private String name;

    /**
     * 福利图片
     */
    private String imageUrl;

    /**
     * 描述
     */
    private String desc;

}
