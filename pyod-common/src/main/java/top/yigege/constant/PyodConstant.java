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




}
