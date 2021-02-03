package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.yigege.dto.modules.shop.QueryNearbyLatestShopDTO;
import top.yigege.dto.modules.shop.QueryNearbyShopPageListDTO;
import top.yigege.model.Label;
import top.yigege.model.Shop;
import top.yigege.dao.ShopMapper;
import top.yigege.model.SysUser;
import top.yigege.service.ILabelService;
import top.yigege.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.yigege.util.PageUtil;
import top.yigege.vo.PageBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    ShopMapper shopMapper;

    @Autowired
    ILabelService iLabelService;

    @Override
    public Shop queryNearbyLatestShop(QueryNearbyLatestShopDTO queryNearbyLatestShopDTO) {
        return shopMapper.queryNearbyLatestShop(queryNearbyLatestShopDTO);
    }

    @Override
    public PageBean queryNearbyShopPageList(QueryNearbyShopPageListDTO queryNearbyShopPageListDTO) {
        Page pageInfo = new Page(queryNearbyShopPageListDTO.getPage(),
                queryNearbyShopPageListDTO.getPageSize() == 0 ? 10 : queryNearbyShopPageListDTO.getPageSize());
        //一对多的场景对一端分页，先对主表分页，再去关联子表
        List<Shop> shopList = shopMapper.queryNearbyShopPageList(queryNearbyShopPageListDTO, pageInfo);
        /*shopList.forEach(item -> {
            LambdaQueryWrapper<Label> labelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            labelLambdaQueryWrapper.eq(La)
            item.setLabelList(iLabelService.list());
        });*/
        return PageUtil.getPageBean(pageInfo, shopList);
    }

    @Override
    public void addDefaultShop(SysUser sysUser) {
        Shop shop = new Shop();
        shop.setMerchantId(Long.valueOf(sysUser.getUserId()));
        shop.setContact(sysUser.getTel());
        save(shop);
    }

    @Override
    public List<Shop> queryShopListByMerchantId(Long merchantId) {
        LambdaQueryWrapper<Shop> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Shop::getMerchantId,merchantId);
        return list(lambdaQueryWrapper);
    }
}
