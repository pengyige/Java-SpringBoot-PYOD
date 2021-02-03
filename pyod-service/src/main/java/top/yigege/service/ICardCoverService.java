package top.yigege.service;

import top.yigege.model.CardCover;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 卡片封面 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-04
 */
public interface ICardCoverService extends IService<CardCover> {

    CardCover queryDefaultCardCover(Long merchantId);

    List<CardCover> queryCardCoverByMerchantId(Long merchantId);
}
