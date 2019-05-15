package cn.herculas.recruit.captcha.service;

import java.awt.image.BufferedImage;

public interface CaptchaService {
    String createCaptcha();
    String findCaptchaContent(String captchaKey);
    BufferedImage createCaptchaImage(String captchaContent);
}
