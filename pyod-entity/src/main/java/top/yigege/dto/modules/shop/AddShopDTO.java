package top.yigege.dto.modules.shop;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: ModifyShopDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 17:35
 */
@Data
public class AddShopDTO {

    /**
     * 商家id
     */
    @NotNull(message = "商家id不能为空")
    private Long merchantId;

    /**
     * 店铺名称
     */
    @NotBlank(message = "店铺名称不能为空")
    private String name;

    /**
     * 详细地址
     */
    @NotBlank(message = "详细地址不能为空")
    private String address;

    /**
     * 城市id
     */
    @NotNull(message = "城市id不能为空")
    private Long cityId;

    /**
     * 经度
     */
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    /**
     * 工作模式(0-周一至周日,1-周一至周五,2-周一至周六,3-周末)
     */
    @NotNull(message = "工作模式不能为空")
    private Integer workMode;

    /**
     * 开始时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空")
    private String contact;

    /**
     * 封面
     */
    private String pic;
}
