package top.yigege.vo.wx;

import lombok.Data;

/**
 * @ClassName: Code2SessionResultBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月05日 17:57
 */
@Data
public class Code2SessionResultBean {

    String openid;

    String session_key;

    String unionid;

    int errcode;

    String errmsg;
}
