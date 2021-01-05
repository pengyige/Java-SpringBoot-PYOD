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
public class ModifyCardCoverDTO {

    @NotNull(message = "封面id不能为空")
    private Long cardCoverId;


    /**
     * 封面地址
     */
    private String imageUrl;

    /**
     * 排序
     */
    private Integer sort;

}
