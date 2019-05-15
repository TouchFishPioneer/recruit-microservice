package cn.herculas.recruit.gateway.util.proxy;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CookieProxy {
    public static Cookie cookieExtractor(HttpServletRequest httpServletRequest, String key) {
        Map<String, Cookie> cookieMap = cookieFormatConverter(httpServletRequest);
        return cookieMap.getOrDefault(key, null);
    }

    private static Map<String, Cookie> cookieFormatConverter(HttpServletRequest httpServletRequest) {
        Map<String, Cookie> result = new HashMap<>();
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                result.put(cookie.getName(), cookie);
            }
        }
        return result;
    }
}
