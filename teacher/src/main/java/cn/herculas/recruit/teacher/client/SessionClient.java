package cn.herculas.recruit.teacher.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "session")
public interface SessionClient {
    @PostMapping("/index")
    String createSession(@RequestParam("uuid") String uuid,
                         @RequestParam("role") String role,
                         @RequestParam("region") String region);

    @GetMapping("/content")
    Map<String, String> findSession(@RequestParam("key") String sessionId);

    @DeleteMapping("/index")
    boolean deleteSession(@RequestParam("key") String sessionId);

}