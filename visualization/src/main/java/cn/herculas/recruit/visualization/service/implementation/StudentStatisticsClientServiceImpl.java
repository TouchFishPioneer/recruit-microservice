package cn.herculas.recruit.visualization.service.implementation;

import cn.herculas.recruit.visualization.client.StudentStatisticsClient;
import cn.herculas.recruit.visualization.service.StudentStatisticsClientService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class StudentStatisticsClientServiceImpl implements StudentStatisticsClientService {

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

    private final StudentStatisticsClient studentStatisticsClient;
    private final StringRedisTemplate stringRedisTemplate;

    public StudentStatisticsClientServiceImpl(StudentStatisticsClient studentStatisticsClient,
                                              StringRedisTemplate stringRedisTemplate) {
        this.studentStatisticsClient = studentStatisticsClient;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    @Scheduled(cron = "0 0 0,12 * * *")
    public void getClientStudentGenderStatistics() {
        Map<Integer, Long> studentGenderStatistics = studentStatisticsClient.getStudentGenderStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_GENDER_KEY,
                JSON.toJSONString(studentGenderStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 5 0,12 * * *")
    public void getClientStudentProvinceStatistics() {
        Map<String, Long> studentProvinceStatistics = studentStatisticsClient.getStudentProvinceStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_PROVINCE_KEY,
                JSON.toJSONString(studentProvinceStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 10 0,12 * * *")
    public void getClientStudentCityStatistics() {
        Map<String, Long> studentCityStatistics = studentStatisticsClient.getStudentCityStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_CITY_KEY,
                JSON.toJSONString(studentCityStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 15 0,12 * * *")
    public void getClientStudentDivisionStatistics() {
        Map<Integer, Long> studentDivisionStatistics = studentStatisticsClient.getStudentDivisionStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_DIVISION_KEY,
                JSON.toJSONString(studentDivisionStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 20 0,12 * * *")
    public void getClientStudentContactStatusStatistics() {
        Map<Integer, Long> studentContactStatusStatistics = studentStatisticsClient.getStudentContactStatusStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_CONTACT_KEY,
                JSON.toJSONString(studentContactStatusStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }

    @Override
    @Scheduled(cron = "0 25 0,12 * * *")
    public void getClientStudentGradeStatistics() {
        Map<Integer, Long> studentGradeStatistics = studentStatisticsClient.getStudentGradeStatistics();
        stringRedisTemplate.opsForValue().set(
                STUDENT_GRADE_KEY,
                JSON.toJSONString(studentGradeStatistics),
                EXPIRES,
                TimeUnit.SECONDS);
    }
}
