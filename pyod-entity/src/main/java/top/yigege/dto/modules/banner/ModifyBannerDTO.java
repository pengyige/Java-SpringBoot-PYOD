package top.yigege.dto.modules.banner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddBannerDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 17:33
 */
@Data
public class ModifyBannerDTO {

    /**
     * 轮播id
     */
    @NotNull(message = "轮播id不能为空")
    private Long bannerId;

    /**
     * 图片Url
     */
    private String imageUrl;

    /**
     * 跳转url
     */
    private String linkUrl;
}
