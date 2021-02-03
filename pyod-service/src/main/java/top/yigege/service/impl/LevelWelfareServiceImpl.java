package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import top.yigege.model.Level;
import top.yigege.model.LevelWelfare;
import top.yigege.dao.LevelWelfareMapper;
import top.yigege.service.ILevelService;
import top.yigege.service.ILevelWelfareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.Utils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class LevelWelfareServiceImpl extends ServiceImpl<LevelWelfareMapper, LevelWelfare> implements ILevelWelfareService {

    @Resource
    LevelWelfareMapper levelWelfareMapper;

    @Autowired
    ILevelService iLevelService;

    @Override
    public List<LevelWelfare> queryLevelWelfareByLevelId(Long levelId) {
        return levelWelfareMapper.queryLevelWelfareByLevelId(levelId);
    }

    @Transactional
    @Override
    public void bindLevelWelfare(Long levelId, String welfareIds) {
        if (StringUtils.isNotBlank(welfareIds)){
            //1.删除该等级下所有绑定记录
            deleteLevelWelfare(levelId);

            List<Integer> list = Utils.parseIntegersList(Utils.splitStringToList(welfareIds));
            List<LevelWelfare> levelWelfareList = new ArrayList<>();
            list.forEach(item-> {
                LevelWelfare levelWelfare = new LevelWelfare();
                levelWelfare.setLevelId(levelId);
                levelWelfare.setWelfareId(Long.valueOf(item));
                levelWelfareList.add(levelWelfare);
            });
            saveBatch(levelWelfareList);
        }
    }

    /**
     * 删除等级福利
     * @param levelId
     */
    public void deleteLevelWelfare(Long levelId) {
        //1.删除该等级下所有绑定记录
        LambdaQueryWrapper<LevelWelfare> levelWelfareLambdaQueryWrapper = new LambdaQueryWrapper<>();
        levelWelfareLambdaQueryWrapper.eq(LevelWelfare::getLevelId,levelId);
        remove(levelWelfareLambdaQueryWrapper);
    }
}
