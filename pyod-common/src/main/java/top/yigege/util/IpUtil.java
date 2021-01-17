package top.yigege.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @ClassName: IpUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月16日 19:38
 */
public class IpUtil {

    /**
     * 获取当前机器的ip
     *
     * @return String
     */
    public static String getLocalIP() throws UnknownHostException {
        String localIP = "";
        InetAddress addr = (InetAddress) InetAddress.getLocalHost();

        localIP = addr.getHostAddress().toString();
        return localIP;
    }
}
