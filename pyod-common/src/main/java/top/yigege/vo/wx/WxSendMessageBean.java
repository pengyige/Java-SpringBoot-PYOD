package top.yigege.vo.wx;

import lombok.Data;

/**
 * @ClassName: WxSendMessageBean
 * @Description:TODO
 * @author: yigege
 * @date: 2021年03月12日 16:11
 */
@Data
public class WxSendMessageBean {

    private String touser;

    private String template_id;

    private String page;

    private String miniprogram_state;

    private WxSendMessageDataBean data;

}
