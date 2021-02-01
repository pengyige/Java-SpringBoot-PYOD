package top.yigege.service;

import top.yigege.model.SysUser;
import top.yigege.model.User;

/**
 * @ClassName: ITokenService
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 16:24
 */
public interface ITokenService {

    /**
     * 根据用户信息生成tokne
     *
     * @param user
     * @return
     */
    String getToken(User user);

    /**
     * 根据商家用户信息生成token
     * @param user
     * @return
     */
    String getToken(SysUser user);

    /**
     * 校验token
     *
     * @param token
     * @return
     */
    boolean checkToken(String token);

    /**
     * 获取用户id
     * @param token
     * @return
     */
    Long getUserId(String token);
}


