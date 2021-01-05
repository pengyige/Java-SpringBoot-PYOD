package top.yigege.dto.modules.cardCover;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCardCoverDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 17:37
 */
@Data
public class AddCardCoverDTO {

    /**
     * 封面地址
     */
    @NotBlank(message = "封面地址不能为空")
    private String imageUrl;

    /**
     * 排序
     */
    @NotNull(message = "排序不能为空")
    private Integer sort;

}
