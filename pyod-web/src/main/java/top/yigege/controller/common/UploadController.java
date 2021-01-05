package top.yigege.controller.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.yigege.constant.ResultCodeEnum;
import top.yigege.util.AliyunOSSUtil;
import top.yigege.util.ApiResultUtil;
import top.yigege.vo.LayuiFileUploadResultBean;
import top.yigege.vo.ResultBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UploadImageController
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月04日 20:01
 */
@RestController
@RequestMapping("/web/upload")
@Slf4j
public class UploadController {

    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @PostMapping("/uploadFile")
    public LayuiFileUploadResultBean uploadFile(@RequestParam("file") MultipartFile file){
        LayuiFileUploadResultBean layuiFileUploadResultBean = new LayuiFileUploadResultBean();
        int code = 0;
        String msg = "";
        Map<String,String> data = new HashMap<>();
        try {
             String src = aliyunOSSUtil.uploadFileInputSteam(file.getOriginalFilename(),file);
            msg = ResultCodeEnum.SUCCESS.getMsg();
            data.put("src",src);
        }catch (Exception e) {
            code = ResultCodeEnum.ERROR.getCode();
            msg = ResultCodeEnum.ERROR.getMsg();
        }
        layuiFileUploadResultBean.setCode(code);
        layuiFileUploadResultBean.setMsg(msg);
        layuiFileUploadResultBean.setData(data);
        return layuiFileUploadResultBean;
    }
}
