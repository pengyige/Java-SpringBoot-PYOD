package top.yigege.controller.cardCover;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.dto.modules.cardCover.AddCardCoverDTO;
import top.yigege.dto.modules.cardCover.ModifyCardCoverDTO;
import top.yigege.model.CardCover;
import top.yigege.service.ICardCoverService;
import top.yigege.util.ApiResultUtil;
import top.yigege.util.Utils;
import top.yigege.vo.LayuiTableResultBean;
import top.yigege.vo.ResultBean;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: WebCardCoverController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 17:53
 */
@RestController
@RequestMapping("/web/cardCover")
@Validated
public class WebCardCoverController {

    @Autowired
    ICardCoverService iCardCoverService;

    @PostMapping("/addCardCover")
    public ResultBean addCardCover(@Valid AddCardCoverDTO addCardCoverDTO){
        CardCover CardCover = new CardCover();
        BeanUtil.copyProperties(addCardCoverDTO, CardCover);

        return ApiResultUtil.success(iCardCoverService.save(CardCover));
    };

    @PostMapping("/modifyCardCover")
    public ResultBean modifyCardCover(@Valid ModifyCardCoverDTO modifyCardCoverDTO) {
        CardCover CardCover = new CardCover();
        BeanUtil.copyProperties(modifyCardCoverDTO, CardCover);

        return ApiResultUtil.success(iCardCoverService.updateById(CardCover));
    }

    @PostMapping("/deleteCardCoverByIds")
    public ResultBean deleteCardCoverByIds(@NotBlank(message = "封面id不能为空") String cardCoverIds) {
        return ApiResultUtil.success(iCardCoverService.removeByIds(Utils.parseIntegersList(Utils.splitStringToList(cardCoverIds))));
    }

    @PostMapping("/queryAllCardCoverList")
    public LayuiTableResultBean queryAllCardCoverList() {

        int code = 0;
        String msg = ResultCodeEnum.SUCCESS.getMsg();
        List<CardCover> CardCoverList = new ArrayList<>();
        try {
            CardCoverList = iCardCoverService.list();
        } catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        return new LayuiTableResultBean(code, msg, CardCoverList.size(), CardCoverList);
    }

    @PostMapping("/queryCardCoverDetail")
    public ResultBean queryCardCoverDetail(@NotNull(message = "封面id不能为空") Long cardCoverId) {
        return ApiResultUtil.success(iCardCoverService.getById(cardCoverId));
    }

}
