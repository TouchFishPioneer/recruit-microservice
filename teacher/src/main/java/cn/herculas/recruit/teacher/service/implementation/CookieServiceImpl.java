package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.service.CookieService;
import cn.herculas.recruit.teacher.util.proxy.CookieProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class CookieServiceImpl implements CookieService {

    @Value("${cookie.key}")
    private String key;
    @Value("${cookie.max-age}")
    private Integer maxAge;

    @Override
    public void generateCookie(HttpServletResponse httpServletResponse, String value) {
        CookieProxy.cookieGenerator(httpServletResponse, key, value, maxAge);
    }

    @Override
    public Cookie findCookie(HttpServletRequest httpServletRequest) {
        return CookieProxy.cookieExtractor(httpServletRequest, key);
    }

    @Override
    public void deleteCookie(HttpServletResponse httpServletResponse) {
        CookieProxy.cookieGenerator(httpServletResponse, key, null, 0);
    }
}
