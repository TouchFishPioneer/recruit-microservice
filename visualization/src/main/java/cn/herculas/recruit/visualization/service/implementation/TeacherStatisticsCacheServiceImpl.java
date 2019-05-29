package cn.herculas.recruit.visualization.service.implementation;

import cn.herculas.recruit.visualization.service.TeacherStatisticsCacheService;
import cn.herculas.recruit.visualization.service.TeacherStatisticsClientService;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherStatisticsCacheServiceImpl implements TeacherStatisticsCacheService {

    @Value("${statistics.expires}")
    private Integer EXPIRES;
    @Value("${statistics.teacher.gender}")
    private String TEACHER_GENDER_KEY;
    @Value("${statistics.teacher.department}")
    private String TEACHER_DEPARTMENT_KEY;
    @Value("${statistics.teacher.region.province}")
    private String TEACHER_PROVINCE_KEY;
    @Value("${statistics.teacher.region.city}")
    private String TEACHER_CITY_KEY;

    private final TeacherStatisticsClientService teacherStatisticsClientService;
    private final StringRedisTemplate stringRedisTemplate;

    public TeacherStatisticsCacheServiceImpl(TeacherStatisticsClientService teacherStatisticsClientService,
                                             StringRedisTemplate stringRedisTemplate) {
        this.teacherStatisticsClientService = teacherStatisticsClientService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public Map<Integer, Long> getCachedTeacherGenderStatistics() throws JSONException {
        String cachedTeacherGenderStatistics = stringRedisTemplate.opsForValue().get(TEACHER_GENDER_KEY);
        if (cachedTeacherGenderStatistics == null) {
            teacherStatisticsClientService.getTeacherGenderStatistics();
            cachedTeacherGenderStatistics = stringRedisTemplate.opsForValue().get(TEACHER_GENDER_KEY);
        }
       return JSONObject.parseObject(cachedTeacherGenderStatistics, new TypeReference<Map<Integer, Long>>() {});
    }

    @Override
    public Map<String, Long> getCachedTeacherDepartmentStatistics() throws JSONException {
        String cachedTeacherDepartmentStatistics = stringRedisTemplate.opsForValue().get(TEACHER_DEPARTMENT_KEY);
        if (cachedTeacherDepartmentStatistics == null) {
            teacherStatisticsClientService.getTeacherDepartmentStatistics();
            cachedTeacherDepartmentStatistics = stringRedisTemplate.opsForValue().get(TEACHER_DEPARTMENT_KEY);
        }
        return JSONObject.parseObject(cachedTeacherDepartmentStatistics, new TypeReference<Map<String, Long>>() {});
    }

    @Override
    public Map<String, Long> getCachedTeacherProvinceStatistics() throws JSONException {
        String cachedTeacherProvinceStatistics = stringRedisTemplate.opsForValue().get(TEACHER_PROVINCE_KEY);
        if (cachedTeacherProvinceStatistics == null) {
            teacherStatisticsClientService.getTeacherProvinceStatistics();
            cachedTeacherProvinceStatistics = stringRedisTemplate.opsForValue().get(TEACHER_PROVINCE_KEY);
        }
        return JSONObject.parseObject(cachedTeacherProvinceStatistics, new TypeReference<Map<String, Long>>() {});
    }

    @Override
    public Map<String, Long> getCachedTeacherCityStatistics() throws JSONException {
        String cachedTeacherCityStatistics = stringRedisTemplate.opsForValue().get(TEACHER_CITY_KEY);
        if (cachedTeacherCityStatistics == null) {
            teacherStatisticsClientService.getTeacherCityStatistics();
            cachedTeacherCityStatistics = stringRedisTemplate.opsForValue().get(TEACHER_CITY_KEY);
        }
        return JSONObject.parseObject(cachedTeacherCityStatistics, new TypeReference<Map<String, Long>>() {});
    }
}
