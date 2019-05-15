package cn.herculas.recruit.gateway.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    Cookie findCookie(HttpServletRequest httpServletRequest);
}
