package cn.herculas.recruit.session.controller;

import cn.herculas.recruit.session.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/index")
    public String createSession(@RequestParam("uuid") String uuid,
                                @RequestParam("role") String role,
                                @RequestParam("region") String region) {

        return sessionService.createSession(uuid, role, region);
    }

    @GetMapping("/content")
    public Map<String, String> findSession(@RequestParam("key") String sessionId) {
        return sessionService.findSession(sessionId);
    }

    @DeleteMapping("/index")
    public boolean deleteSession(@RequestParam("key") String sessionId) {
        return sessionService.deleteSession(sessionId);
    }
}
