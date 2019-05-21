package cn.herculas.recruit.student.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "captcha")
public interface CaptchaClient {
    @PostMapping("/confirm")
    boolean confirmCaptcha(@RequestParam("key") String captchaKey,
                           @RequestParam("content") String captchaContent);
}
