package top.yigege.dto.modules.city;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddCityDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月21日 15:55
 */
@Data
public class ModifyCityDTO {

    @NotNull(message = "城市id不能为空")
    private Long cityId;

    /**
     * 父id
     */
    private Long pid;

    /**
     * 城市名
     */
    private String name;

    /**
     * 类型(0-国家,1-省,2-市,3-县)
     */
    private Integer type;

    /**
     * 热门城市(0-非热门,1-热门)
     */
    private Integer hotFlag;

}
