package top.yigege.util;

import org.apache.commons.lang3.StringUtils;
import top.yigege.model.SysDict;
import top.yigege.service.ISysDictService;
import top.yigege.service.IRedisService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DictUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2020年11月02日 15:00
 */
public class DictUtil {

    @Resource
    ISysDictService iDictService;

    @Resource
    IRedisService iRedisService;

    /**
     * 缓存的字典
     */
    private Map<String, String> cacheDicMap = new HashMap<>();

    private DictUtil(){

    }

    private static class SignletonHolder {
        private static DictUtil INSTANCE = new DictUtil();
    }

    public static final DictUtil getInstance() {
        return SignletonHolder.INSTANCE;
    }


    /**
     * 通过code查询数据值
     * @param code
     * @return
     */
    public String getValueByDB(String code) {

        SysDict dict = iDictService.queryDictByCode(code);
        if (null != dict) {
            return dict.getCode();
        }else {
            return "";
        }
    }

    /**
     * 获取值
     * @param code
     * @return
     */
    public String getValue(String code) {
        String value = cacheDicMap.get(code);
        if (StringUtils.isBlank(value)) {
            //1.从redis获取
            String redisValue = (String) iRedisService.getObj(code);
            if (StringUtils.isBlank(redisValue)) {
                //从数据库获取
                String dbValue = getValueByDB(code);
                //加入缓存
                cacheDicMap.put(code, dbValue);
                iRedisService.setObj(code,dbValue);

                value = dbValue;
            }else {
                //加入缓存
                cacheDicMap.put(code, redisValue);
                value =  redisValue;
            }
        }
        return value;
    }
}
