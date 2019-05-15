package cn.herculas.recruit.session.service;

import java.util.Map;

public interface SessionService {
    String createSession(String uuid, String role, String region);
    Map<String, String> findSession(String sessionId);
    boolean deleteSession(String sessionId);
}
