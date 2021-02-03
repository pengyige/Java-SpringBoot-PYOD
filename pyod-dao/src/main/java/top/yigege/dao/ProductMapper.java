package top.yigege.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.yigege.dto.modules.product.QueryProductPageListDTO;
import top.yigege.model.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 商品 Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> queryProductWithCoupon(Long couponActivityId);

    Product queryProductDetailWithCoupon(Long couponActivityId, Long productId);

    List<Product> queryProductPageList(QueryProductPageListDTO queryProductPageListDTO, Page pageInfo);
}
