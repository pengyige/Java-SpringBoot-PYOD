package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.label.QueryLabelPageListDTO;
import top.yigege.model.Label;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 标签 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface LabelMapper extends BaseMapper<Label> {

    List<Label> queryLabelPageList(QueryLabelPageListDTO queryLabelPageListDTO, Page pageInfo);
}
