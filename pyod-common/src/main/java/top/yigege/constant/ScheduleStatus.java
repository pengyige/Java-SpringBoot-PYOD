package top.yigege.constant;

/**
 * @ClassName: ScheduleStatus
 * @Description:TODO
 * @author: yigege
 * @date: 2020年12月13日 16:44
 */
public enum ScheduleStatus {
    /**
     * 暂停
     */
    PAUSE(0),
    /**
     * 正常
     */
    NORMAL(1);

    private int value;

    ScheduleStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

