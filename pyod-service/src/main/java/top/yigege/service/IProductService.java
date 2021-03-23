package top.yigege.service;

import top.yigege.dto.modules.product.QueryProductPageListDTO;
import top.yigege.model.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;
import top.yigege.vo.wx.WxPayInfoBean;

import java.util.List;

/**
 * <p>
 * 商品 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IProductService extends IService<Product> {

    /**
     * 查询当前商家所有商品信息
     * @param merchantId
     * @return
     */
    List<Product> queryAllProductInfo(Long merchantId);

    List<Product> queryProductWithCoupon(Long merchantId,Long couponActivityId);

    Product queryProductDetailWithCoupon(Long couponActivityId, Long productId);


    /**
     * 购买商品
     *
     * @param productId
     * @return
     */
    public WxPayInfoBean buy(Long productId, Long userId, Long vipCardId);

    /**
     * 购买商品完成处理
     *
     * @param orderNo
     */
    void buyProductFinishHanlder(String orderNo);

    /**
     * 查下商品分页
     * @param queryProductPageListDTO
     * @return
     */
    PageBean queryProductPageList(QueryProductPageListDTO queryProductPageListDTO);
}
