package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.client.SessionClient;
import cn.herculas.recruit.teacher.service.SessionService;
import org.springframework.stereotype.Service;

import java.util.Map;

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
    public Map<String, String> findSession(String sessionId) {
        return sessionClient.findSession(sessionId);
    }

    @Override
    public boolean deleteSession(String sessionId) {
        return sessionClient.deleteSession(sessionId);
    }
}
