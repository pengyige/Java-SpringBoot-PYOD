package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName: FestivalTypeEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月22日 21:41
 */
@AllArgsConstructor
@Getter
public enum FestivalTypeEnum {

    ZHONG_QIU(1,"中秋节"),

    YUAN_DAN(2,"元旦"),

    SHUANG_SHI_YI(3,"双十一"),

    QING_REN(4,"情人节"),

    FU_NV(5,"妇女节"),

    YU_REN(6,"愚人节"),

    LAO_DONG(7,"劳动节"),

    QIN_NIAN(8,"青年节"),

    ER_TONG(9,"儿童节"),

    GUO_QING(10,"国庆节"),

    SEHN_DAN(11,"圣诞节"),

    CHUN_JIE(12,"春节"),

    YUAN_XIAO(13,"元宵节"),

    DUAN_WU(14,"端午节"),

    QI_XI(15,"七夕情人节"),

    XIAO_NIAN(16,"小年"),

    CHU_XI(17,"除夕"),

    LIU_YI_BA(18,"618节");

    private Integer code;

    private String desc;

    public static FestivalTypeEnum getByDesc(String desc) {
        FestivalTypeEnum festivalTypeEnum = null;
        if (StringUtils.isNotBlank(desc)) {
            return null;
        }

        for (FestivalTypeEnum item : FestivalTypeEnum.values()) {
            if (item.getDesc().equals(desc)) {
                return item;
            }
        }

        return festivalTypeEnum;
    }
}
