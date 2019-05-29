package cn.herculas.recruit.visualization.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value = "teacher")
public interface TeacherStatisticsClient {
    @GetMapping("/statistics/gender")
    Map<Integer, Long> getTeacherGenderStatistics();
    @GetMapping("/statistics/department")
    Map<String, Long> getTeacherDepartmentStatistics();
    @GetMapping("/statistics/province")
    Map<String, Long> getTeacherProvinceStatistics();
    @GetMapping("/statistics/city")
    Map<String, Long> getTeacherCityStatistics();
}
