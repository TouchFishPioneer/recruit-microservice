package cn.herculas.recruit.student.service.implementation;

import cn.herculas.recruit.student.client.SessionClient;
import cn.herculas.recruit.student.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService {

    private final SessionClient sessionClient;

    public SessionServiceImpl(SessionClient sessionClient) {
        this.sessionClient = sessionClient;
    }

    @Override
    public String generateSession(String uuid, String role, String region) {
        return sessionClient.createSession(uuid, role, region);
    }

    @Override
    public boolean deleteSession(String sessionId) {
        return sessionClient.deleteSession(sessionId);
    }
}
