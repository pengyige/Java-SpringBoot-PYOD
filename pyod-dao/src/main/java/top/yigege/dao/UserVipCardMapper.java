package top.yigege.dao;

import top.yigege.dto.modules.userVipCard.QueryUserVipCardInfoResDTO;
import top.yigege.model.UserVipCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
public interface UserVipCardMapper extends BaseMapper<UserVipCard> {

    List<QueryUserVipCardInfoResDTO> queryUserVipCardList(Long userId);

    QueryUserVipCardInfoResDTO queryUserVipCardDetail(Long vipCardId);
}
