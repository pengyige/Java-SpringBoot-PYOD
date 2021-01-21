package top.yigege.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.sysJob.QuerySysJobPageListDTO;
import top.yigege.model.SysJob;

import java.util.List;

/**
 * <p>
 * 系统任务 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
public interface SysJobMapper extends BaseMapper<SysJob> {

    List<SysJob> querySysJobPageList(QuerySysJobPageListDTO querySysJobPageListDTO, Page pageInfo);
}
