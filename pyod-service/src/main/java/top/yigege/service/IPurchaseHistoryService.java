package top.yigege.service;

import top.yigege.model.PurchaseHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IPurchaseHistoryService extends IService<PurchaseHistory> {

    /**
     * 查询购买历史
     * @param orderNo
     * @return
     */
    PurchaseHistory queryPurchaseDetail(String orderNo);
}
