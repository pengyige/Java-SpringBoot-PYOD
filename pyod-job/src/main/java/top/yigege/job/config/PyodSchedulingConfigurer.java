package top.yigege.job.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import top.yigege.model.SysJob;
import top.yigege.service.ISysJobService;
import top.yigege.util.SpringUtil;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName: PyodSchedulingConfigurer
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月19日 14:46
 */
@Component
@Slf4j
public class PyodSchedulingConfigurer implements SchedulingConfigurer {

    @Autowired
    ISysJobService iSysJobService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        List<SysJob> sysJobList = iSysJobService.queryRunSysJobList();
        sysJobList.forEach(item -> {
            Runnable runnable=new Runnable() {
                @Override
                public void run() {
                    Class<?> clazz;
                    try {
                        clazz = Class.forName(item.getClassName());
                        String className = lowerFirstCapse(clazz.getSimpleName());
                        Object bean = (Object) SpringUtil.getBean(className);
                        Method method = ReflectionUtils.findMethod(bean.getClass(), item.getMethodName());
                        log.info("任务{}已启动",item.getName());
                        ReflectionUtils.invokeMethod(method, bean);
                        log.info("任务{}已结束",item.getName());
                    } catch (ClassNotFoundException e) {
                        log.error(e.getMessage(),e);
                    }
                }
            };
            scheduledTaskRegistrar.addCronTask(runnable,item.getCron());
            log.info("任务{}已加载",item.getName());
        });
    }

    /**
     * 转换首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
