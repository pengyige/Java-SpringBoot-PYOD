package top.yigege.dto.modules.product;

import lombok.Data;

/**
 * @ClassName: QueryProductPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月28日 15:46
 */
@Data
public class QueryProductPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
