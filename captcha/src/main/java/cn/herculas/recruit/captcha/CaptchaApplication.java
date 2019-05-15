package cn.herculas.recruit.captcha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CaptchaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CaptchaApplication.class, args);
    }
}
