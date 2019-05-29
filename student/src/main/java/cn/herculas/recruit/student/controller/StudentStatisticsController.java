package cn.herculas.recruit.student.controller;

import cn.herculas.recruit.student.service.StudentStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StudentStatisticsController {

    private final StudentStatisticsService studentStatisticsService;

    public StudentStatisticsController(StudentStatisticsService studentStatisticsService) {
        this.studentStatisticsService = studentStatisticsService;
    }

    @GetMapping("/gender")
    public Map<Integer, Long> getStudentGenderStatistics() {
        return studentStatisticsService.groupByGender();
    }

    @GetMapping("/province")
    public Map<String, Long> getStudentProvinceStatistics() {
        return studentStatisticsService.groupByProvince();
    }

    @GetMapping("/city")
    public Map<String, Long> getStudentCityStatistics() {
        return studentStatisticsService.groupByCity();
    }

    @GetMapping("/division")
    public Map<Integer, Long> getStudentDivisionStatistics() {
        return studentStatisticsService.groupByDivision();
    }

    @GetMapping("/contact")
    public Map<Integer, Long> getStudentContactStatusStatistics() {
        return studentStatisticsService.groupByContactStatus();
    }

    @GetMapping("/grade")
    public Map<Integer, Long> getStudentGradeStatistics() {
        return studentStatisticsService.groupByGrade();
    }
}
