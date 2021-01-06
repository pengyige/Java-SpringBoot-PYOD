package top.yigege.service;

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
    String getUserId(String token);
}


