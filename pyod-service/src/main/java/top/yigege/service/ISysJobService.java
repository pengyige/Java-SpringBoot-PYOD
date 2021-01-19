package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.SysJob;

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

    List<SysJob> queryRunSysJobList();
}
