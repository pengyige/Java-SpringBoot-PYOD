package top.yigege.dto.modules.welfare;

import lombok.Data;

/**
 * @ClassName: QueryWelfarePageListDTO
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月29日 20:44
 */
@Data
public class QueryWelfarePageListDTO {

    int page = 1;

    int pageSize = 10;

    Long merchantId;
}
