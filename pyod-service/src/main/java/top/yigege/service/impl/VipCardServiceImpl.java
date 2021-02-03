package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import top.yigege.constant.BusinessFlagEnum;
import top.yigege.model.VipCard;
import top.yigege.dao.VipCardMapper;
import top.yigege.service.IGenerateIDService;
import top.yigege.service.IVipCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class VipCardServiceImpl extends ServiceImpl<VipCardMapper, VipCard> implements IVipCardService {

    @Autowired
    IGenerateIDService iGenerateIDService;

    @Override
    public VipCard queryVipCardByCardId(Long vipCardId) {
        LambdaQueryWrapper<VipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(VipCard::getVipCardId,vipCardId);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public VipCard queryVipCardByCardNo(String cardNo) {
        LambdaQueryWrapper<VipCard> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(VipCard::getCardNo,cardNo);
        return getOne(lambdaQueryWrapper);
    }

    @Override
    public VipCard generatorVipCard(Long merchantId,Long cardCoverId) {
        VipCard vipCard = new VipCard();
        vipCard.setName("PYOD");
        vipCard.setMerchantId(merchantId);
        vipCard.setCardCoverId(cardCoverId);
        vipCard.setCardNo(iGenerateIDService.getNo(""));
        vipCard.setRemark("在线购卡系统自动生成");
        save(vipCard);
        return vipCard;
    }
}
