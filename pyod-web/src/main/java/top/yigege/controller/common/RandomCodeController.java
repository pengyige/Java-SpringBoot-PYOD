package top.yigege.controller.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.yigege.PyodApplication;
import top.yigege.constant.PyodConstant;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @ClassName: RandomCodeController
 * @Description:TODO
 * @author: yigege
 * @date: 2020年09月17日 17:04
 */
@Controller
@RequestMapping("/random")
@Slf4j
public class RandomCodeController {


    private static int WIDTH = 80;          //宽
    private static int HEIGHT = 25;         //高

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("/generate")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(true);

        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        try {
            ServletOutputStream sos = response.getOutputStream();

            BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.getGraphics();
            char[] rands = generateCheckCode();

            drawBackground(g);
            drawRands(g, rands);
            g.dispose();

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(image, "JPEG", bos);
            byte[] buf = bos.toByteArray();
            response.setContentLength(buf.length);

            sos.write(buf);
            bos.close();
            sos.close();
            String verifyCode = new String(rands);
            session.setAttribute(PyodConstant.PyodKey.SESSION_RANDOM_CODE, verifyCode);
        } catch (Exception e) {
            log.error( e.getMessage(),e);
        }

        return null;
    }

    //获取随机数
    private char[] generateCheckCode() {
        String chars = "0123456789qwertyuiopasdfghjklzxcvbnm";
        char[] rands = new char[4];
        for (int i = 0; i < 4; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }

    //画随机串
    private void drawRands(Graphics g, char[] rands) {

        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 20));

        g.drawString("" + rands[0], 3, 17);
        g.drawString("" + rands[1], 20, 15);
        g.drawString("" + rands[2], 40, 20);
        g.drawString("" + rands[3], 60, 16);
    }

    //背景阴影
    private void drawBackground(Graphics g) {

        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * WIDTH);
            int y = (int) (Math.random() * HEIGHT);
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            g.setColor(new Color(red, green, blue));
            g.drawOval(x, y, 1, 0);
        }
    }

}
