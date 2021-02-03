package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.welfare.QueryWelfarePageListDTO;
import top.yigege.model.Welfare;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface WelfareMapper extends BaseMapper<Welfare> {

    List<Welfare> queryWelfarePageList(QueryWelfarePageListDTO queryWelfarePageListDTO, Page pageInfo);
}
