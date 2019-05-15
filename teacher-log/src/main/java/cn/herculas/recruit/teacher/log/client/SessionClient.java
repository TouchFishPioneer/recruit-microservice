package cn.herculas.recruit.teacher.log.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "session")
public interface SessionClient {
    @PostMapping("/index")
    String createSession(@RequestParam("uuid") String uuid,
                         @RequestParam("role") String role,
                         @RequestParam("region") String region);

    @DeleteMapping("/index")
    boolean deleteSession(@RequestParam("key") String sessionId);
}
