package top.yigege.controller.banner;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.banner.AddBannerDTO;
import top.yigege.dto.modules.banner.ModifyBannerDTO;
import top.yigege.model.Banner;
import top.yigege.service.IBannerService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.PageBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: WebBannerController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 17:53
 */
@RestController
@RequestMapping("/web/banner")
@Validated
public class WebBannerController {

    @Autowired
    IBannerService iBannerService;

    @PostMapping("/addBanner")
    public ResultBean addBanner(@Valid AddBannerDTO addBannerDTO){
        Banner banner = new Banner();
        BeanUtil.copyProperties(addBannerDTO, banner);

        return ApiResultUtil.success(iBannerService.save(banner));
    };

    @PostMapping("/modifyBanner")
    public ResultBean modifyBanner(@Valid ModifyBannerDTO modifyBannerDTO) {
        Banner banner = new Banner();
        BeanUtil.copyProperties(modifyBannerDTO, banner);

        return ApiResultUtil.success(iBannerService.updateById(banner));
    }

    @PostMapping("/deleteBannerByIds")
    public ResultBean deleteBannerByIds(@NotBlank(message = "轮播id不能为空") String bannerIds) {
        return ApiResultUtil.success(iBannerService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(bannerIds))));
    }

    @PostMapping("/queryAllBannerList")
    public LayuiTableResultBean queryAllBannerList() {

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        List<Banner> bannerList = new ArrayList<>();
        try {
            bannerList = iBannerService.list();
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, bannerList.size(), bannerList);
    }

    @PostMapping("/queryBannerDetail")
    public ResultBean queryBannerDetail(@NotNull(message = "轮播id不能为空") Long bannerId) {
        return ApiResultUtil.success(iBannerService.getById(bannerId));
    }

}
