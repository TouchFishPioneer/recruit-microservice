package cn.herculas.recruit.gateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "session")
public interface SessionClient {
    @GetMapping("/content")
    Map<String, String> findSession(@RequestParam("key") String sessionId);
}
