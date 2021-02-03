package top.yigege.service;

import top.yigege.dto.modules.welfare.QueryWelfarePageListDTO;
import top.yigege.model.Welfare;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface IWelfareService extends IService<Welfare> {

    PageBean queryWelfarePageList(QueryWelfarePageListDTO queryWelfarePageListDTO);
}
