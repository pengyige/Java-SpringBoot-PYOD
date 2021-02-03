package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.welfare.QueryWelfarePageListDTO;
import top.yigege.model.Welfare;
import top.yigege.dao.WelfareMapper;
import top.yigege.service.IWelfareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class WelfareServiceImpl extends ServiceImpl<WelfareMapper, Welfare> implements IWelfareService {

    @Resource
    WelfareMapper welfareMapper;

    @Override
    public PageBean queryWelfarePageList(QueryWelfarePageListDTO queryWelfarePageListDTO) {

        Page pageInfo = new Page(queryWelfarePageListDTO.getPage(),
                queryWelfarePageListDTO.getPageSize() == 0 ? 10 : queryWelfarePageListDTO.getPageSize());
        List<Welfare> welfareList = welfareMapper.queryWelfarePageList(queryWelfarePageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, welfareList);

    }
}
