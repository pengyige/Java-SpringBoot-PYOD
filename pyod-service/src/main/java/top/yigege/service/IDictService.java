package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.Dict;
import top.yigege.vo.PageBean;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2020-11-02
 */
public interface IDictService extends IService<Dict> {

    /**
     * 通过code查询字典
     * @param code
     * @return
     */
    Dict queryDictByCode(String code);

    /**
     * 查询字典列表
     * @param page
     * @param pageSize
     * @param paramMap
     * @return
     */
    PageBean queryDictList(int page, int pageSize, Map paramMap);
}
