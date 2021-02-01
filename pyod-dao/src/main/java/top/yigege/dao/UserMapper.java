package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.yigege.dto.modules.console.QueryHomeDataResDTO;
import top.yigege.model.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-05
 */
public interface UserMapper extends BaseMapper<User> {

    QueryHomeDataResDTO queryHomeData(Integer merchantId);
}
