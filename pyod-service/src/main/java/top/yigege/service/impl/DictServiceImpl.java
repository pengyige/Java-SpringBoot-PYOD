package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.dao.DictMapper;
import top.yigege.model.Dict;
import top.yigege.model.User;
import top.yigege.service.IDictService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2020-11-02
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements IDictService {

    @Resource
    DictMapper dictMapper;

    @Override
    public Dict queryDictByCode(String code) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper();
        return getOne(queryWrapper.eq("code",code));
    }

    @Override
    public PageBean queryDictList(int page, int pageSize, Map paramMap) {
        Page pageInfo = new Page(page, pageSize == 0 ? 10 : pageSize);
        List<Dict> dicts = dictMapper.queryAllDict(paramMap, pageInfo);
        return PageUtil.getPageBean(pageInfo, dicts);
    }
}
