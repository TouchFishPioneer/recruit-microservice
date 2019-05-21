package cn.herculas.recruit.teacher.service;

import java.util.Map;

public interface SessionService {
    String generateSession(String uuid, String role, String region);
    Map<String, String> findSession(String sessionId);
    boolean deleteSession(String sessionId);
}
