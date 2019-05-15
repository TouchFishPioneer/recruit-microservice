package cn.herculas.recruit.gateway.service.implementation;

import cn.herculas.recruit.gateway.client.SessionClient;
import cn.herculas.recruit.gateway.service.SessionService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Map;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionClient sessionClient;

    public SessionServiceImpl(SessionClient sessionClient) {
        this.sessionClient = sessionClient;
    }

    @Override
    public String getUuidFromSession(Cookie cookie) {
        Map<String, String> sessionContent = sessionClient.findSession(cookie.getValue());
        if (sessionContent == null) return null;
        return sessionContent.getOrDefault("uuid", null);
    }

    @Override
    public String getRoleFromSession(Cookie cookie) {
        Map<String, String> sessionContent = sessionClient.findSession(cookie.getValue());
        if (sessionContent == null) return null;
        return sessionContent.getOrDefault("role", null);
    }
}
