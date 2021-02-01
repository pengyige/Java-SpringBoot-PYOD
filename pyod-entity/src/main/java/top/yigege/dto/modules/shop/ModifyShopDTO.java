package top.yigege.dto.modules.shop;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName: ModifyShopDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月30日 17:35
 */
@Data
public class ModifyShopDTO {

    @NotNull(message = "店铺id不能为空")
    private Long shopId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 城市id
     */
    private Long cityId;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 工作模式(0-周一至周日,1-周一至周五,2-周一至周六,3-周末)
     */
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
    private String contact;

    /**
     * 封面
     */
    private String pic;
}
