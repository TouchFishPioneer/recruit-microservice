package cn.herculas.recruit.teacher.log.service.implementation;

import cn.herculas.recruit.teacher.log.client.SessionClient;
import cn.herculas.recruit.teacher.log.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionClient sessionClient;

    public SessionServiceImpl(SessionClient sessionClient) {
        this.sessionClient = sessionClient;
    }

    @Override
    public String generateTeacherSession(String uuid, String role, String region) {
        return sessionClient.createSession(uuid, role, region);
    }

    @Override
    public boolean deleteTeacherSession(String sessionId) {
        return sessionClient.deleteSession(sessionId);
    }
}
