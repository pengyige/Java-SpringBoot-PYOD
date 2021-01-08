package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.City;

/**
 * <p>
 * 城市表 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-08
 */
public interface ICityService extends IService<City> {


    /**
     * 查询城市
     * @param province
     * @param city
     * @param district
     * @return
     */
    City queryCityDetail (String province, String city, String district);

}
