package cn.herculas.recruit.captcha.util.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class CaptchaConfiguration {
    @Bean
    public DefaultKaptcha getDefaultCaptcha() {
        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        Properties properties = new Properties();

        properties.setProperty("captcha.border", "no");
        properties.setProperty("captcha.image.width", "180");
        properties.setProperty("captcha.image.height", "50");
        properties.setProperty("captcha.textProducer.font.size", "45");
        properties.setProperty("captcha.textProducer.char.length", "4");
        properties.setProperty("captcha.session.key", "code");

        captchaProducer.setConfig(new Config(properties));
        return captchaProducer;
    }
}
