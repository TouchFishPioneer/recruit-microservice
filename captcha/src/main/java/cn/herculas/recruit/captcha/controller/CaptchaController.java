package cn.herculas.recruit.captcha.controller;

import cn.herculas.recruit.captcha.data.VO.ResponseVO;
import cn.herculas.recruit.captcha.service.CaptchaService;
import cn.herculas.recruit.captcha.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    @GetMapping("/key")
    public ResponseVO createCaptcha() {
        String captchaKey = captchaService.createCaptcha();
        Map<String, String> result = new HashMap<>();
        result.put("key", captchaKey);
        return ResponseWrapper.success(result);
    }

    @GetMapping("/image")
    public void getCaptchaImage(@RequestParam("key") String captchaKey,
                                HttpServletResponse response) throws IOException {
        if (captchaKey == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return;
        }
        String captchaContent = captchaService.findCaptchaContent(captchaKey);
        if (captchaContent == null) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
        BufferedImage captchaImage = captchaService.createCaptchaImage(captchaContent);
        ImageIO.write(captchaImage, "JPEG", response.getOutputStream());
    }

    @PostMapping("/confirm")
    public boolean confirmCaptcha(@RequestParam("key") String captchaKey,
                                  @RequestParam("content") String captchaContent) {

        String contentFromRedis = captchaService.findCaptchaContent(captchaKey);
        return contentFromRedis != null && contentFromRedis.equals(captchaContent);
    }
}
