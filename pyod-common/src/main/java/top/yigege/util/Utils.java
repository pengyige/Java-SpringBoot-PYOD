package top.yigege.util;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import top.yigege.constant.PyodConstant;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Utils
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月25日 9:26
 */
public class Utils {

    private Utils(){

    }

    /**
     * 分割String转List
     * @param str
     * @return
     */
    public static List<String> splitStringToList(String str) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }

        String[] strArr = str.split(PyodConstant.Common.BATCH_SPLIT_FLAG);

        return  Arrays.asList(strArr);
    }


    /**
     *
     * @param StringList
     * @return
     */
    public static List<Integer> parseIntegersList(List<String> StringList) {
        List<Integer> IntegerList = new ArrayList<Integer>();
        for (String x : StringList) {
            Integer z = Integer.parseInt(x);
            IntegerList.add(z);
        }
        return IntegerList;
    }

    /**
     *
     * @param StringList
     * @return
     */
    public static List<Long> parseLongList(List<String> StringList) {
        List<Long> longList = new ArrayList<Long>();
        for (String x : StringList) {
            Long z = Long.parseLong(x);
            longList.add(z);
        }
        return longList;
    }


}
