package top.yigege.service;

import top.yigege.model.LevelWelfare;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ILevelWelfareService extends IService<LevelWelfare> {

    List<LevelWelfare> queryLevelWelfareByLevelId(Long levelId);

    /**
     * 绑定等级福利
     *
     * @param levelId
     * @param welfareIds
     */
    void bindLevelWelfare(Long levelId, String welfareIds);

    /**
     * 删除等级福利
     *
     * @param levelId
     */
    void deleteLevelWelfare(Long levelId);
}

