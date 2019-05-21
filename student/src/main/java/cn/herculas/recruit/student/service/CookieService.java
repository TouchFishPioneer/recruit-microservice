package cn.herculas.recruit.student.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieService {
    Cookie findCookie(HttpServletRequest httpServletRequest);
    void generateCookie(HttpServletResponse httpServletResponse, String value);
    void deleteCookie(HttpServletResponse httpServletResponse);
}
