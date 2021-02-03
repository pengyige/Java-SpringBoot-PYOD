package top.yigege.dao;

import top.yigege.model.LevelWelfare;
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
public interface LevelWelfareMapper extends BaseMapper<LevelWelfare> {

    List<LevelWelfare> queryLevelWelfareByLevelId(Long levelId);
}
