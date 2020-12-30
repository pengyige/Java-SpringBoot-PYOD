package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.yigege.model.Dict;
import top.yigege.model.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2020-11-02
 */
public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> queryAllDict(Map paramMap, IPage iPage);
}
