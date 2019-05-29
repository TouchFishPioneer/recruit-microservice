package cn.herculas.recruit.visualization.service.implementation;

import cn.herculas.recruit.visualization.client.TeacherStatisticsClient;
import cn.herculas.recruit.visualization.service.TeacherStatisticsClientService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TeacherStatisticsClientServiceImpl implements TeacherStatisticsClientService {

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

    private final StringRedisTemplate stringRedisTemplate;
    private final TeacherStatisticsClient teacherStatisticsClient;

    public TeacherStatisticsClientServiceImpl(StringRedisTemplate stringRedisTemplate,
                                              TeacherStatisticsClient teacherStatisticsClient) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.teacherStatisticsClient = teacherStatisticsClient;
    }

    @Override
    @Scheduled(cron = "0 30 0,12 * * *")
    public void getTeacherGenderStatistics() {
        Map<Integer, Long> teacherGenderStatistics = teacherStatisticsClient.getTeacherGenderStatistics();
        stringRedisTemplate.opsForValue().set(
                TEACHER_GENDER_KEY,
                JSON.toJSONString(teacherGenderStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 35 0,12 * * *")
    public void getTeacherDepartmentStatistics() {
        Map<String, Long> teacherDepartmentStatistics = teacherStatisticsClient.getTeacherDepartmentStatistics();
        stringRedisTemplate.opsForValue().set(
                TEACHER_DEPARTMENT_KEY,
                JSON.toJSONString(teacherDepartmentStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 40 0,12 * * *")
    public void getTeacherProvinceStatistics() {
        Map<String, Long> teacherProvinceStatistics = teacherStatisticsClient.getTeacherProvinceStatistics();
        stringRedisTemplate.opsForValue().set(
                TEACHER_PROVINCE_KEY,
                JSON.toJSONString(teacherProvinceStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 45 0,12 * * *")
    public void getTeacherCityStatistics() {
        Map<String, Long> teacherCityStatistics = teacherStatisticsClient.getTeacherCityStatistics();
        stringRedisTemplate.opsForValue().set(
                TEACHER_CITY_KEY,
                JSON.toJSONString(teacherCityStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }
}
