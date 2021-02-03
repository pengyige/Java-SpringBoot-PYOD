package top.yigege.service;

import top.yigege.model.VipCard;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IVipCardService extends IService<VipCard> {

    /**
     * 通过卡片id查询卡片信息
     * @param vipCardId
     * @return
     */
    VipCard queryVipCardByCardId(Long vipCardId);

    /**
     * 通过卡片id查询卡片信息
     * @param cardNo
     * @return
     */
    VipCard queryVipCardByCardNo(String cardNo);

    /**
     * 生成一张vip卡
     * @param merchantId
     * @param cardCoverId
     * @return
     */
    VipCard generatorVipCard(Long merchantId,Long cardCoverId);

}
