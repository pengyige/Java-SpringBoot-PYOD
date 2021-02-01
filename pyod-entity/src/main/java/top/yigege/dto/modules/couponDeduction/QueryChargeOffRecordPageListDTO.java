package top.yigege.dto.modules.couponDeduction;

import lombok.Data;

/**
 * @ClassName: QueryChargeOffRecordPageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月31日 15:59
 */
@Data
public class QueryChargeOffRecordPageListDTO {

    int page = 1;

    int pageSize = 10;

    Long shopId;
}
