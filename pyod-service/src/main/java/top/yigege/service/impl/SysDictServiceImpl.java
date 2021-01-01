package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import top.yigege.dao.SysDictMapper;

import top.yigege.model.SysDict;

import top.yigege.service.ISysDictService;
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
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {

    @Resource
    SysDictMapper dictMapper;

    @Override
    public SysDict queryDictByCode(String code) {
        LambdaQueryWrapper<SysDict> queryWrapper = new LambdaQueryWrapper();
        return getOne(queryWrapper.eq(SysDict::getCode,code));
    }

    @Override
    public PageBean queryDictList(int page, int pageSize, Map paramMap) {
        Page pageInfo = new Page(page, pageSize == 0 ? 10 : pageSize);
        List<SysDict> dicts = dictMapper.queryAllDict(paramMap, pageInfo);
        return PageUtil.getPageBean(pageInfo, dicts);
    }
}
