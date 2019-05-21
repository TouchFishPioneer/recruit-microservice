package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.client.CaptchaClient;
import cn.herculas.recruit.teacher.service.CaptchaService;
import org.springframework.stereotype.Service;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    private final CaptchaClient captchaClient;

    public CaptchaServiceImpl(CaptchaClient captchaClient) {
        this.captchaClient = captchaClient;
    }

    @Override
    public boolean validateCaptcha(String key, String content) {
        return captchaClient.confirmCaptcha(key, content);
    }
}
