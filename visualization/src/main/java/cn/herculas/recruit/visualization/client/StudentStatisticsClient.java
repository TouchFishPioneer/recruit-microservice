package cn.herculas.recruit.visualization.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "student")
public interface StudentStatisticsClient {
    @GetMapping("/statistics/gender")
    Map<Integer, Long> getStudentGenderStatistics();
    @GetMapping("/statistics/province")
    Map<String, Long> getStudentProvinceStatistics();
    @GetMapping("/statistics/city")
    Map<String, Long> getStudentCityStatistics();
    @GetMapping("/statistics/division")
    Map<Integer, Long> getStudentDivisionStatistics();
    @GetMapping("/statistics/contact")
    Map<Integer, Long> getStudentContactStatusStatistics();
    @GetMapping("/statistics/grade")
    Map<Integer, Long> getStudentGradeStatistics();
}
