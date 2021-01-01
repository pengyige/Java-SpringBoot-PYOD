package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import top.yigege.model.SysDict;


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
public interface SysDictMapper extends BaseMapper<SysDict> {

    List<SysDict> queryAllDict(Map paramMap, IPage iPage);
}
