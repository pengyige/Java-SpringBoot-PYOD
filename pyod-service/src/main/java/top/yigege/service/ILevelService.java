package top.yigege.service;

import top.yigege.dto.modules.level.QueryLevelPageListDTO;
import top.yigege.model.Level;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ILevelService extends IService<Level> {

    List<Level> queryLevelByMerchantId(Long merchantId);

    /**
     * 查询默认等级
     * @return
     */
    Level queryDefaultLevel(Long merchantId);

    /**
     * 查询等级分页列表
     * @param queryLevelPageListDTO
     * @return
     */
    PageBean queryLevelPageList(QueryLevelPageListDTO queryLevelPageListDTO);

}
