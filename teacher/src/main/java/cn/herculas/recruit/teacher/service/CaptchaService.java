package cn.herculas.recruit.teacher.service;

public interface CaptchaService {
    boolean validateCaptcha(String key, String content);
}
