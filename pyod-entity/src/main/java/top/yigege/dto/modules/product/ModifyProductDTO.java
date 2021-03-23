package top.yigege.dto.modules.product;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @ClassName: AddProductDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月28日 15:43
 */
@Data
public class ModifyProductDTO {

    @NotNull(message = "商品id不能为空")
    private Long productId;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private Double price;

    /**
     * 商品类型(1-购买券包，2-充值返券)
     */
    private Integer productType;

    /**
     * 使用描述
     */
    private String useDesc;
}
