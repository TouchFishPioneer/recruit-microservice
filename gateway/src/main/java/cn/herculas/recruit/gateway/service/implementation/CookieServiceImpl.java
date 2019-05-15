package cn.herculas.recruit.gateway.service.implementation;

import cn.herculas.recruit.gateway.service.CookieService;
import cn.herculas.recruit.gateway.util.proxy.CookieProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Service
public class CookieServiceImpl implements CookieService {

    @Value("${cookie.key}")
    private String key;

    @Override
    public Cookie findCookie(HttpServletRequest httpServletRequest) {
        return CookieProxy.cookieExtractor(httpServletRequest, key);
    }
}
