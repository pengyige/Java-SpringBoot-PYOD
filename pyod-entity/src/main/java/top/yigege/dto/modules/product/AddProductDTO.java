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
public class AddProductDTO {

    /**
     * 商家id
     */
    private Long merchantId;

    /**
     * 名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String name;

    /**
     * 使用描述
     */
    private String useDesc;

    /**
     * 价格
     */
    @NotNull(message = "商品价格不能为空")
    private Double price;

    /**
     * 商品类型(1-购买券包，2-充值返券)
     */
    @NotNull(message = "商品类型")
    private Integer productType;
}
