package cn.herculas.recruit.visualization.service.implementation;

import cn.herculas.recruit.visualization.service.StudentStatisticsCacheService;
import cn.herculas.recruit.visualization.service.StudentStatisticsClientService;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentStatisticsCacheServiceImpl implements StudentStatisticsCacheService {

    @Value("${statistics.expires}")
    private Integer EXPIRES;
    @Value("${statistics.student.gender}")
    private String STUDENT_GENDER_KEY;
    @Value("${statistics.student.region.province}")
    private String STUDENT_PROVINCE_KEY;
    @Value("${statistics.student.region.city}")
    private String STUDENT_CITY_KEY;
    @Value("${statistics.student.division}")
    private String STUDENT_DIVISION_KEY;
    @Value("${statistics.student.contact}")
    private String STUDENT_CONTACT_KEY;
    @Value("${statistics.student.grade}")
    private String STUDENT_GRADE_KEY;

    private final StringRedisTemplate stringRedisTemplate;
    private final StudentStatisticsClientService studentStatisticsClientService;

    public StudentStatisticsCacheServiceImpl(StringRedisTemplate stringRedisTemplate,
                                             StudentStatisticsClientService studentStatisticsClientService) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.studentStatisticsClientService = studentStatisticsClientService;
    }

    @Override
    public Map<Integer, Long> getCachedStudentGenderStatistics() throws JSONException {
        String cachedStudentGenderStatistics = stringRedisTemplate.opsForValue().get(STUDENT_GENDER_KEY);
        if (cachedStudentGenderStatistics == null) {
            studentStatisticsClientService.getClientStudentGenderStatistics();
            cachedStudentGenderStatistics = stringRedisTemplate.opsForValue().get(STUDENT_GENDER_KEY);
        }
        return JSONObject.parseObject(cachedStudentGenderStatistics, new TypeReference<Map<Integer, Long>>() {});
    }

    @Override
    public Map<String, Long> getCachedStudentProvinceStatistics() throws JSONException {
        String cachedStudentProvinceStatistics = stringRedisTemplate.opsForValue().get(STUDENT_PROVINCE_KEY);
        if (cachedStudentProvinceStatistics == null) {
            studentStatisticsClientService.getClientStudentProvinceStatistics();
            cachedStudentProvinceStatistics = stringRedisTemplate.opsForValue().get(STUDENT_PROVINCE_KEY);
        }
        return JSONObject.parseObject(cachedStudentProvinceStatistics, new TypeReference<Map<String, Long>>() {});
    }

    @Override
    public Map<String, Long> getCachedStudentCityStatistics() throws JSONException {
        String cachedStudentCityStatistics = stringRedisTemplate.opsForValue().get(STUDENT_CITY_KEY);
        if (cachedStudentCityStatistics == null) {
            studentStatisticsClientService.getClientStudentCityStatistics();
            cachedStudentCityStatistics = stringRedisTemplate.opsForValue().get(STUDENT_CITY_KEY);
        }
        return JSONObject.parseObject(cachedStudentCityStatistics, new TypeReference<Map<String, Long>>() {});
    }

    @Override
    public Map<Integer, Long> getCachedStudentDivisionStatistics() throws JSONException {
        String cachedStudentDivisionStatistics = stringRedisTemplate.opsForValue().get(STUDENT_DIVISION_KEY);
        if (cachedStudentDivisionStatistics == null) {
            studentStatisticsClientService.getClientStudentDivisionStatistics();
            cachedStudentDivisionStatistics = stringRedisTemplate.opsForValue().get(STUDENT_DIVISION_KEY);
        }
        return JSONObject.parseObject(cachedStudentDivisionStatistics, new TypeReference<Map<Integer, Long>>() {});

    }

    @Override
    public Map<Integer, Long> getCachedStudentContactStatusStatistics() throws JSONException {
        String cachedStudentContactStatusStatistics = stringRedisTemplate.opsForValue().get(STUDENT_CONTACT_KEY);
        if (cachedStudentContactStatusStatistics == null) {
            studentStatisticsClientService.getClientStudentContactStatusStatistics();
            cachedStudentContactStatusStatistics = stringRedisTemplate.opsForValue().get(STUDENT_CONTACT_KEY);
        }
        return JSONObject.parseObject(cachedStudentContactStatusStatistics, new TypeReference<Map<Integer, Long>>() {});
    }

    @Override
    public Map<Integer, Long> getCachedStudentGradeStatistics() throws JSONException {
        String cachedStudentGradeStatistics = stringRedisTemplate.opsForValue().get(STUDENT_GRADE_KEY);
        if (cachedStudentGradeStatistics == null) {
            studentStatisticsClientService.getClientStudentGradeStatistics();
            cachedStudentGradeStatistics = stringRedisTemplate.opsForValue().get(STUDENT_GRADE_KEY);
        }
        return JSONObject.parseObject(cachedStudentGradeStatistics, new TypeReference<Map<Integer, Long>>() {});
    }
}
