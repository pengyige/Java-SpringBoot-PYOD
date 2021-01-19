package top.yigege.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName: JobStatusEnum
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月11日 15:34
 */
@Getter
@AllArgsConstructor
public enum JobStatusEnum {

    CREATE(1,"已创建"),

    RUN(2,"运行中"),

    STOP(3,"已停止");

    private Integer code;

    private String desc;

}
