package cn.herculas.recruit.student.service;

public interface SessionService {
    String generateSession(String uuid, String role, String region);
    boolean deleteSession(String sessionId);
}
