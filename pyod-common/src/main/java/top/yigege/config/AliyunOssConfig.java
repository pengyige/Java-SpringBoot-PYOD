package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AliyunOssConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 15:59
 */
@Component
@Data
public class AliyunOssConfig {

    /**
     * 阿里云的配置参数
     */
    @Value("${aliyun.oss.accessKeyID}")
    private  String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private  String accessKeySecret;

    @Value("${aliyun.oss.endPoint}")
    private  String endpoint;

    @Value("${aliyun.oss.buckets}")
    private  String bucketName;
    /**
     * 存储在OSS中的前缀名
     */
    @Value("${aliyun.oss.prefix}")
    private  String file_prefix;
}
