package cn.herculas.recruit.teacher.log.service;

public interface SessionService {
    String generateTeacherSession(String uuid, String role, String region);
    boolean deleteTeacherSession(String sessionId);
}
