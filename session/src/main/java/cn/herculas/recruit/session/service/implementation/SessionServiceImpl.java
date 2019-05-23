package cn.herculas.recruit.session.service.implementation;

import cn.herculas.recruit.session.service.SessionService;
import cn.herculas.recruit.session.util.generator.KeyGenerator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SessionServiceImpl implements SessionService {

    @Value("${session.expires}")
    private Integer EXPIRES;
    @Value("${session.prefix}")
    private String SESSION_KEY_PREFIX;

    private final StringRedisTemplate stringRedisTemplate;

    public SessionServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public String createSession(String uuid, String role, String region) {
        String sessionId = SESSION_KEY_PREFIX + KeyGenerator.uuidGenerator().substring(0, 20);

        Map<String, String> sessionContent = new HashMap<>();
        sessionContent.put("uuid", uuid);
        sessionContent.put("role", role);
        sessionContent.put("region", region);

        String sessionValue = JSON.toJSONString(sessionContent);
        stringRedisTemplate.opsForValue().set(sessionId, sessionValue, EXPIRES, TimeUnit.SECONDS);
        return sessionId;
    }

    @Override
    public Map<String, String> findSession(String sessionId) {
        String content = stringRedisTemplate.opsForValue().get(sessionId);

        try {
            return JSONObject.parseObject(content, new TypeReference<Map<String, String>>(){});
        } catch (JSONException e) {
            return null;
        }
    }

    @Override
    public boolean deleteSession(String sessionId) {
        try {
            stringRedisTemplate.opsForValue().getOperations().delete(sessionId);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
