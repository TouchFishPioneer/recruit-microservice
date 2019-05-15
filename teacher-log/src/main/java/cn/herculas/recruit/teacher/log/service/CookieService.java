package cn.herculas.recruit.teacher.log.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {
    void generateCookie(HttpServletResponse httpServletResponse, String value);
    Cookie findCookie(HttpServletRequest httpServletRequest);
    void deleteCookie(HttpServletResponse httpServletResponse);
}
