package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import top.yigege.model.CardCover;
import top.yigege.dao.CardCoverMapper;
import top.yigege.service.ICardCoverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 卡片封面 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-04
 */
@Service
public class CardCoverServiceImpl extends ServiceImpl<CardCoverMapper, CardCover> implements ICardCoverService {


    @Override
    public CardCover queryDefaultCardCover(Long merchantId) {
        LambdaQueryWrapper<CardCover> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(CardCover::getMerchantId,merchantId);
        return list(lambdaQueryWrapper).get(0);
    }

    @Override
    public List<CardCover> queryCardCoverByMerchantId(Long merchantId) {
        LambdaQueryWrapper<CardCover> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(CardCover::getMerchantId,merchantId);
        return list(lambdaQueryWrapper);
    }
}
