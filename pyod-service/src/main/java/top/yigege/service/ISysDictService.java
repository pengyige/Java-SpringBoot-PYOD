package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.SysDict;
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
public interface ISysDictService extends IService<SysDict> {

    /**
     * 通过code查询字典
     *
     * @param code
     * @return
     */
    SysDict queryDictByCode(String code);

    /**
     * 查询字典列表
     *
     * @param page
     * @param pageSize
     * @param paramMap
     * @return
     */
    PageBean queryDictList(int page, int pageSize, Map paramMap);

    String getValueByDB(String code);

    String getValue(String code);
}

