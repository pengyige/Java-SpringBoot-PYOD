package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.constant.JobStatusEnum;
import top.yigege.dao.SysJobMapper;
import top.yigege.model.SysJob;
import top.yigege.service.ISysJobService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 系统任务 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements ISysJobService {

    @Resource
    SysJobMapper sysJobMapper;

    @Override
    public List<SysJob> queryRunSysJobList() {
        LambdaQueryWrapper<SysJob> sysJobLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysJobLambdaQueryWrapper.eq(SysJob::getStatus, JobStatusEnum.RUN.getCode());
        return list(sysJobLambdaQueryWrapper);
    }
}
