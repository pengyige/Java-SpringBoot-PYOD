package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.level.QueryLevelPageListDTO;
import top.yigege.model.Level;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface LevelMapper extends BaseMapper<Level> {

    List<Level> queryLevelPageList(QueryLevelPageListDTO queryLevelPageListDTO, Page pageInfo);
}
