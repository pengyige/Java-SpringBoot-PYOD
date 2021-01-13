package top.yigege.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * @ClassName: QrCodeConfig
 * @Description:TODO
 * @author: yigege
 * @date: 2021年01月11日 13:33
 */
@Data
@Component
public class QrCodeConfig {

    @Value("${qrCode.secret}")
    private String secret;

    @Value("${qrCode.duration}")
    private long duration;
}
