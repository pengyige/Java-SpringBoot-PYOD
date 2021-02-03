package top.yigege.service.impl;

import top.yigege.model.UserLabel;
import top.yigege.dao.UserLabelMapper;
import top.yigege.service.IUserLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户标签 服务实现类
 * </p>
 *
 * @author yigege
 * @since 2021-01-12
 */
@Service
public class UserLabelServiceImpl extends ServiceImpl<UserLabelMapper, UserLabel> implements IUserLabelService {

}
