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

    /**
     * 微信api
     */
    public interface  WeiXin {

        /**
         * 微信接口地址
         */
        public final static String CODE2SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={secret}" +
                "&grant_type=authorization_code&js_code={js_code}";

        /**
         * 获取微信token
         */
        public final static String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appId}&secret={secret}";

        /**
         * 获取小程序二维码
         */
        public final static String GET_WX_ACODE = "https://api.weixin.qq.com/wxa/getwxacode?access_token={access_token}";
    }

    /**
     * 天气api
     */
    public interface WeatherApi {

        /**
         * 实时天气
         */
        String REALTIME_URL = "https://tianqiapi.com/api?version=v6&appid={appid}&appsecret={appsecret}";

        /**
         * 7日天气
         */
        String SERVEN_DAYS_URL = "https://tianqiapi.com/api?version=v1&appid={appid}&appsecret={appsecret}";

        /**
         * 降水量预报图
         */
        String PRECIPITATION_URL = "https://tianqiapi.com/api?version=v8&appid={appid}&appsecret={appsecret}";
    }

    /**
     * 高德api
     */
    public interface GaodeApi {

        /**
         * 逆地址编码接口
         */
        String REGEO_URL = "https://restapi.amap.com/v3/geocode/regeo?key={key}";

    }


    /**
     * JWT 常量
     */
    public interface  JWT {

        String USER_ID = "userId";
    }

    /**
     * api请求公共参数
     */
    public interface ApiRequestCommonParam {

        String TIMESTAMP = "timestamp";

        String NONCE = "nonce";

        String SIGN = "sign";

        String TOKEN = "token";
    }

}
