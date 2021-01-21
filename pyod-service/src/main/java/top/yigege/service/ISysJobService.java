package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.dto.modules.sysJob.QuerySysJobPageListDTO;
import top.yigege.model.SysJob;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 * 系统任务 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
public interface ISysJobService extends IService<SysJob> {
    /**
     * 查询运行中所有任务
     * @return
     */
    List<SysJob> queryRunSysJobList();

    /**
     * 查询任务分页列表
     * @param querySysJobPageListDTO
     * @return
     */
    PageBean querySysJobPageList(QuerySysJobPageListDTO querySysJobPageListDTO);
}
