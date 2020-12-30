package top.yigege.service;

/**
 * @ClassName: IRedisService
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 16:22
 */
public interface IRedisService {

    void setObj(String key, Object obj);

    void setObj(String key, Object obj, long timeout);

    Object getObj(String key);
}
