package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.level.QueryLevelPageListDTO;
import top.yigege.model.Label;
import top.yigege.model.Level;
import top.yigege.dao.LevelMapper;
import top.yigege.service.ILevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
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
public class LevelServiceImpl extends ServiceImpl<LevelMapper, Level> implements ILevelService {

    @Resource
    LevelMapper levelMapper;

    @Override
    public List<Level> queryLevelByMerchantId(Long merchantId) {
        //等级信息
        LambdaQueryWrapper<Level> levelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        levelLambdaQueryWrapper.eq(Level::getMerchantId,merchantId);
        List<Level> levelList = list(levelLambdaQueryWrapper);
        levelList.sort((level1,level2) -> {
            return level1.getMinValue() - level2.getMinValue();
        });
        return levelList;
    }

    @Override
    public Level queryDefaultLevel(Long merchantId) {
        //等级信息
        LambdaQueryWrapper<Level> levelLambdaQueryWrapper = new LambdaQueryWrapper<>();
        levelLambdaQueryWrapper.eq(Level::getMerchantId,merchantId);
        List<Level> levelList = list();
        levelList.sort((level1,level2) -> {
            return level1.getMinValue() - level2.getMinValue();
        });
        return levelList.get(0);
    }

    @Override
    public PageBean queryLevelPageList(QueryLevelPageListDTO queryLevelPageListDTO) {
        Page pageInfo = new Page(queryLevelPageListDTO.getPage(),
                queryLevelPageListDTO.getPageSize() == 0 ? 10 : queryLevelPageListDTO.getPageSize());
        List<Level> levelList = levelMapper.queryLevelPageList(queryLevelPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, levelList);
    }
}
