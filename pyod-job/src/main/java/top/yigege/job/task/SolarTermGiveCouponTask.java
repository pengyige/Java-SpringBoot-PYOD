package top.yigege.job.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.model.CouponActivity;
import top.yigege.service.ICouponActivityService;

/**
 * @ClassName: FestivalGiveCouponTask
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月19日 22:59
 */
@Component
@Slf4j
public class SolarTermGiveCouponTask {

    @Autowired
    ICouponActivityService iCouponActivityService;

    /**
     * 执行方法
     */
    public void run(){
        log.info("节气送券run...");
        CouponActivity couponActivity = iCouponActivityService.queryUnderwayActivity(ActivityTypeEnum.SOLAR_TERM);
        if (null != couponActivity) {
            log.info("开始送券");
        }

    }

}
