package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.shop.QueryNearbyLatestShopDTO;
import top.yigege.dto.modules.shop.QueryNearbyShopPageListDTO;
import top.yigege.model.Label;
import top.yigege.model.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ShopMapper extends BaseMapper<Shop> {

    List<Label> selectLabelByShopId(Long shopId);

    Shop queryNearbyLatestShop(QueryNearbyLatestShopDTO queryNearbyLatestShopDTO);

    List<Shop> queryNearbyShopPageList(QueryNearbyShopPageListDTO queryNearbyShopPageListDTO, Page pageInfo);
}
