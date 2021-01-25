package top.yigege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.Banner;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-04
 */
public interface IBannerService extends IService<Banner> {


    List<Banner> queryBannerByMerchantId(Long merchantId);
}
