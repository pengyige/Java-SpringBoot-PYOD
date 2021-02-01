package top.yigege.controller.product;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ActivityStatusEnum;
import top.yigege.constant.ActivityTypeEnum;
import top.yigege.constant.ResultCodeEnum;

import top.yigege.dto.modules.product.AddProductDTO;
import top.yigege.dto.modules.product.ModifyProductDTO;
import top.yigege.dto.modules.product.QueryProductPageListDTO;
import top.yigege.exception.BusinessException;
import top.yigege.model.Product;
import top.yigege.service.IProductService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.SessionUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 商品 前端控制器
 * </p>
 *
 * @author yigege
 * @since 2021-01-19
 */
@Api(tags = "商品管理(WEB)")
@RestController
@RequestMapping("/web/product")
@Validated
public class WebProductController {

    @Autowired
    IProductService iProductService;

    @ApiOperation("添加商品")
    @PostMapping("/addProduct")
    public ResultBean addProduct(@Valid AddProductDTO addProductDTO){
        Product product = new Product();
        BeanUtil.copyProperties(addProductDTO, product);
        product.setPrice(product.getPrice()*100);
        product.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        return ApiResultUtil.success(iProductService.save(product));
    };

    @ApiOperation("修改商品")
    @PostMapping("/modifyProduct")
    public ResultBean modifyProduct(@Valid ModifyProductDTO modifyProductDTO) {
        Product Product = new Product();
        BeanUtil.copyProperties(modifyProductDTO, Product);
        Product.setPrice(Product.getPrice()*100);
        return ApiResultUtil.success(iProductService.updateById(Product));
    }

    @ApiOperation("删除商品")
    @PostMapping("/deleteProductByIds")
    public ResultBean deleteProductByIds(@NotBlank(message = "商品id不能为空") String productIds) {
        return ApiResultUtil.success(iProductService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(productIds))));
    }

    @ApiOperation("查询商品分页列表")
    @PostMapping("/queryProductPageList")
    public LayuiTableResultBean queryProductPageList(QueryProductPageListDTO queryProductPageListDTO) {
        queryProductPageListDTO.setMerchantId(Long.valueOf(SessionUtil.getUser().getUserId()));
        PageBean pageBean = new PageBean();

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        try {
            pageBean = iProductService.queryProductPageList(queryProductPageListDTO);
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, pageBean.getTotalCount(), pageBean.getData());
    }

    @ApiOperation("查询商品详情")
    @PostMapping("/queryProductDetail")
    public ResultBean queryProductDetail(@NotNull(message = "商品id不能为空") Long productId) {
        return ApiResultUtil.success(iProductService.getById(productId));
    }

    @ApiOperation("查询所有商品")
    @PostMapping("/queryAllProductList")
    public ResultBean queryAllProductList() {
        LambdaQueryWrapper<Product> productLambdaQueryWrapper = new LambdaQueryWrapper<>();
        productLambdaQueryWrapper.eq(Product::getMerchantId,SessionUtil.getUser().getUserId());
        return ApiResultUtil.success(iProductService.list(productLambdaQueryWrapper));
    }

}
