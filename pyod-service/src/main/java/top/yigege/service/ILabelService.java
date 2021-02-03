package top.yigege.service;

import top.yigege.dto.modules.label.QueryLabelPageListDTO;
import top.yigege.model.Label;
import com.baomidou.mybatisplus.extension.service.IService;
import top.yigege.vo.PageBean;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface ILabelService extends IService<Label> {

    /**
     * 查询标签分页列表
     * @param queryLabelPageListDTO
     * @return
     */
    PageBean queryLabelPageList(QueryLabelPageListDTO queryLabelPageListDTO);
}
