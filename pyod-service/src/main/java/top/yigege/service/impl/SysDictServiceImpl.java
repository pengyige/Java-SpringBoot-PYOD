package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import top.yigege.dao.SysDictMapper;

import top.yigege.model.SysDict;

import top.yigege.service.IRedisService;
import top.yigege.service.ISysDictService;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    IRedisService iRedisService;

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


    /**
     * 通过code查询数据值
     *
     * @param code
     * @return
     */
    @Override
    public String getValueByDB(String code) {
        SysDict dict = queryDictByCode(code);
        if (null != dict) {
            return dict.getValue();
        } else {
            return "";
        }
    }

    /**
     * 获取值
     *
     * @param code
     * @return
     */
    @Override
    public String getValue(String code) {
        //1.从redis获取
        String value = "";
        String redisValue = (String) iRedisService.getObj(code);
        if (StringUtils.isBlank(redisValue)) {
            //从数据库获取
            String dbValue = getValueByDB(code);
            iRedisService.setObj(code, dbValue);

            value = dbValue;
        } else {
            value = redisValue;
        }

        return value;
    }
}
