package top.yigege.constant;

/**
 * @ClassName: PyodConstant
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月17日 17:06
 */
public class PyodConstant {

    /**
     * key
     */
    public interface PyodKey {

        /**
         * session 验证码
         */
        String SESSION_RANDOM_CODE = "session_random_code";

        /**
         * session 用户
         */
        String SESSION_USER_KEY = "session_user_key";

        /**
         * 当前用户选中角色
         */
        String SESSION_USER_CHECKED_ROLE = "session_user_checked_role";
    }

    public interface Common {

        String BASE_PACKAGE = "top.yigege";

        /**
         * 批量分割符
         */
        String BATCH_SPLIT_FLAG = ",";

        /**
         * 根菜单标识
         */
        int PARENT_MENU_FLAG = -1;

        /**
         * 全部
         */
        int ALL = -1;
    }

    public interface  WeiXin {

        /**
         * 微信接口地址
         */
        public final static String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={secret}" +
                "&grant_type=authorization_code&js_code=";

        /**
         * 获取微信token
         */
        public final static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={secret}";

        /**
         * 获取小程序二维码
         */
        public final static String GET_WX_ACODE = "https://api.weixin.qq.com/wxa/getwxacode?access_token={access_token}";
    }




}
