package top.yigege.service;

/**
 * @ClassName: IGenerateIDService
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月28日 17:34
 */
public interface IGenerateIDService {

    /**
     * 根据业务获取编号
     * @param businessFlag
     * @return
     */
    String getNo(String businessFlag);
}
