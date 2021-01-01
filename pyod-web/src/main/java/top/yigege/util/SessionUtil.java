package top.yigege.util;

import top.yigege.constant.PyodConstant;
import top.yigege.model.SysRole;
import top.yigege.model.SysUser;


import java.util.UUID;

/**
 * @ClassName: SessionUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年10月13日 16:05
 */
public class SessionUtil {

    /**
     * 当前是否登录
     *
     * @return
     */
    public static boolean isLogin() {
        return null != SessionUtil.getUser();
    }

    /**
     * 获取session中的用户信息
     *
     * @return User
     */
    public static SysUser getUser() {
        return (SysUser) RequestHolder.getSession(PyodConstant.PyodKey.SESSION_USER_KEY);
    }

    /**
     * 添加session
     *
     * @param user
     */
    public static void setUser(SysUser user) {
        RequestHolder.setSession(PyodConstant.PyodKey.SESSION_USER_KEY, user);
    }

    /**
     * 删除session信息
     */
    public static void removeUser() {
        RequestHolder.removeSession(PyodConstant.PyodKey.SESSION_USER_KEY);
    }


    /**
     * 添加角色session
     *
     * @param role
     */
    public static void setCurrentUserRole(SysRole role) {
        RequestHolder.setSession(PyodConstant.PyodKey.SESSION_USER_CHECKED_ROLE, role);
    }

    /**
     * 返回角色session
     *
     */
    public static SysRole getCurrentUserRole() {
       return (SysRole) RequestHolder.getSession(PyodConstant.PyodKey.SESSION_USER_CHECKED_ROLE);
    }

    /**
     * 获取session中的Token信息
     *
     * @return String
     */
    public static String getToken(String key) {
        return (String) RequestHolder.getSession(key);
    }

    /**
     * 添加Token
     */
    public static void setToken(String key) {
        RequestHolder.setSession(key, UUID.randomUUID().toString());
    }

    /**
     * 删除Token信息
     */
    public static void removeToken(String key) {
        RequestHolder.removeSession(key);
    }

    /**
     * 获取验证码
     */
    public static String getKaptcha() {
        return (String) RequestHolder.getSession(PyodConstant.PyodKey.SESSION_RANDOM_CODE);
    }

    /**
     * 保存验证码
     */
    public static void setKaptcha(String kaptcha) {
        RequestHolder.setSession(PyodConstant.PyodKey.SESSION_RANDOM_CODE,kaptcha);
    }

    /**
     * 保存验证码
     */
    public static void removeKaptcha() {
        RequestHolder.removeSession(PyodConstant.PyodKey.SESSION_RANDOM_CODE);
    }

    /**
     * 删除所有的session信息
     */
    public static void removeAllSession() {
        String[] keys = RequestHolder.getSessionKeys();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                RequestHolder.removeSession(key);
            }
        }
    }
}
