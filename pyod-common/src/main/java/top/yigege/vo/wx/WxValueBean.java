package top.yigege.vo.wx;

import lombok.Data;

/**
 * @ClassName: WxValueBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年03月12日 16:14
 */
@Data
public class WxValueBean {

    private String value;

    public WxValueBean(String value) {
        this.value = value;
    }
}
