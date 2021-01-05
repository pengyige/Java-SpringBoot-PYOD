package top.yigege.dto.modules.banner;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddBannerDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 17:33
 */
@Data
public class AddBannerDTO {

    /**
     * 图片Url
     */
    @NotNull(message = "图片url不能为空")
    private String imageUrl;

    /**
     * 跳转url
     */
    @NotNull(message = "跳转url不能为空")
    private String linkUrl;
}
