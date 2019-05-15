package cn.herculas.recruit.teacher.log.service;

public interface CaptchaService {
    boolean validateCaptcha(String key, String content);
}
