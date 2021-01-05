package top.yigege.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import top.yigege.config.AliyunOssConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @ClassName: AliyunOSSUtil
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 15:34
 */
@Component
@Slf4j
public class AliyunOSSUtil {

    @Autowired
    AliyunOssConfig aliyunOssConfig;


    /**
     * 获取图片的URL头信息
     *
     * @return 返回url头信息
     */
    private  String getURLHead() {
        //从哪个位置截取
        int cutPoint = aliyunOssConfig.getEndpoint().lastIndexOf('/') + 1;
        //http头
        String head = aliyunOssConfig.getEndpoint().substring(0, cutPoint);
        //服务器地址信息
        String tail = aliyunOssConfig.getEndpoint().substring(cutPoint);
        //返回结果
        return head + aliyunOssConfig.getBucketName() + "." + tail + "/";
    }

    /**
     * 获取存储在服务器上的地址
     *
     * @param oranName 文件名
     * @return 文件URL
     */
    private  String getRealName(String oranName) {
        return getURLHead() + oranName;
    }

    /**
     * 获取一个随机的文件名
     *
     * @param oranName 初始的文件名
     * @return 返回加uuid后的文件名
     */
    private  String getRandomImageName(String oranName) {
        //获取一个uuid 去掉-
        String uuid = UUID.randomUUID().toString().replace("-", "");
        //查一下是否带路径
        int cutPoint = oranName.lastIndexOf("/") + 1;
        //如果存在路径
        if (cutPoint != 0) {
            //掐头 如果开头是/ 则去掉
            String head = oranName.indexOf("/") == 0 ? oranName.substring(1, cutPoint) : oranName.substring(0, cutPoint);
            //去尾
            String tail = oranName.substring(cutPoint);
            //返回正确的带路径的图片名称
            return aliyunOssConfig.getFile_prefix() + head + uuid + tail;
        }
        //不存在 直接返回
        return aliyunOssConfig.getFile_prefix() + uuid + oranName;
    }

    /**
     * MultipartFile2File
     * @param multipartFile
     * @return
     */
    private File transferToFile(MultipartFile multipartFile) {
        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            //获取文件名
            String originalFilename = multipartFile.getOriginalFilename();
            //获取最后一个"."的位置
            int cutPoint = originalFilename.lastIndexOf(".");
            //获取文件名
            String prefix = originalFilename.substring(0,cutPoint);
            //获取后缀名
            String suffix = originalFilename.substring(cutPoint + 1);
            //创建临时文件
            file = File.createTempFile(prefix, suffix);
            //multipartFile2file
            multipartFile.transferTo(file);
            //删除临时文件
            file.deleteOnExit();
        } catch (IOException e) {
           log.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public  String uploadFileInputSteam(String oranFileName, MultipartFile file) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = getRandomImageName(oranFileName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint()
                , aliyunOssConfig.getAccessKeyId()
                , aliyunOssConfig.getAccessKeySecret());

        // 上传文件流
        try (InputStream inputStream = new FileInputStream(transferToFile(file))) {
            //上传到OSS
            ossClient.putObject(aliyunOssConfig.getBucketName(), objectName, inputStream);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回文件在服务器上的全路径+名称
        return getRealName(objectName);
    }


    /**
     * 上传文件流
     *
     * @param oranFileName 上传到服务器上的文件路径和名称
     * @param file         来自本地的文件或者文件流
     */
    public  String uploadFileInputSteam(String oranFileName, File file) {

        // <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg
        String objectName = getRandomImageName(oranFileName);

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(aliyunOssConfig.getEndpoint()
                , aliyunOssConfig.getAccessKeyId()
                , aliyunOssConfig.getAccessKeySecret());
        // 上传文件流。
        try (InputStream inputStream = new FileInputStream(file);) {
            //上传到OSS
            ossClient.putObject(aliyunOssConfig.getBucketName(), objectName, inputStream);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回文件在服务器上的全路径+名称
        return getRealName(objectName);
    }

}
