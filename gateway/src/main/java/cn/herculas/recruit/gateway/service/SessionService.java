package cn.herculas.recruit.gateway.service;

import javax.servlet.http.Cookie;

public interface SessionService {
    String getUuidFromSession(Cookie cookie);
    String getRoleFromSession(Cookie cookie);
}
