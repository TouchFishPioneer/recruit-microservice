package cn.herculas.recruit.student.service;

public interface CaptchaService {
    boolean validateCaptcha(String key, String content);
}
