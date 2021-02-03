package top.yigege.service;

import top.yigege.dto.modules.shop.QueryNearbyLatestShopDTO;
import top.yigege.dto.modules.shop.QueryNearbyShopPageListDTO;
import top.yigege.model.Shop;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.model.SysUser;
import top.yigege.vo.PageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IShopService extends IService<Shop> {

    /**
     * 查询最近的店铺信息
     * @param queryNearbyLatestShopDTO
     * @return
     */
    Shop queryNearbyLatestShop(QueryNearbyLatestShopDTO queryNearbyLatestShopDTO);

    /**
     * 查询附近店铺信息
     * @param queryNearbyShopPageListDTO
     * @return
     */
    PageBean queryNearbyShopPageList(QueryNearbyShopPageListDTO queryNearbyShopPageListDTO);


    /**
     * 添加默认商铺
     * @param sysUser
     */
    public void addDefaultShop(SysUser sysUser) ;

    /**
     * 查询商家下所有店铺
     * @param merchantId
     * @return
     */
    List<Shop> queryShopListByMerchantId(Long merchantId);
}
