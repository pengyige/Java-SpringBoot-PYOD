package top.yigege.dto.modules.user;

import lombok.Data;

/**
 * @ClassName: QueryOwnConsumptionRecordDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月23日 12:16
 */
@Data
public class QueryOwnConsumptionRecordDTO {

    int page = 1;

    int pageSize = 2;

    Long userId;
}
