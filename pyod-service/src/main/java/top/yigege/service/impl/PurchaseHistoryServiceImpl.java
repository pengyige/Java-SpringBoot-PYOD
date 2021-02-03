package top.yigege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import top.yigege.model.PurchaseHistory;
import top.yigege.dao.PurchaseHistoryMapper;
import top.yigege.service.IPurchaseHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class PurchaseHistoryServiceImpl extends ServiceImpl<PurchaseHistoryMapper, PurchaseHistory> implements IPurchaseHistoryService {

    @Resource
    PurchaseHistoryMapper purchaseHistoryMapper;

    @Override
    public PurchaseHistory queryPurchaseDetail(String orderNo) {
        LambdaQueryWrapper<PurchaseHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(PurchaseHistory::getOrderNo, orderNo);
        return getOne(lambdaQueryWrapper);
    }
}
