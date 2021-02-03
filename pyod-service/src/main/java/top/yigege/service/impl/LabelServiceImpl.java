package top.yigege.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.label.QueryLabelPageListDTO;
import top.yigege.model.Label;
import top.yigege.dao.LabelMapper;
import top.yigege.model.SysJob;
import top.yigege.service.ILabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {

    @Resource
    LabelMapper labelMapper;

    @Override
    public PageBean queryLabelPageList(QueryLabelPageListDTO queryLabelPageListDTO) {
        Page pageInfo = new Page(queryLabelPageListDTO.getPage(),
                queryLabelPageListDTO.getPageSize() == 0 ? 10 : queryLabelPageListDTO.getPageSize());
        List<Label> labels = labelMapper.queryLabelPageList(queryLabelPageListDTO, pageInfo);
        return PageUtil.getPageBean(pageInfo, labels);
    }
}
