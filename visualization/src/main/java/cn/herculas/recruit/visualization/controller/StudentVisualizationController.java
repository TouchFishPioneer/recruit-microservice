package cn.herculas.recruit.visualization.controller;

import cn.herculas.recruit.visualization.data.VO.ResponseVO;
import cn.herculas.recruit.visualization.service.StudentStatisticsCacheService;
import cn.herculas.recruit.visualization.util.wrapper.ResponseWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentVisualizationController {

    private final StudentStatisticsCacheService studentStatisticsCacheService;

    public StudentVisualizationController(StudentStatisticsCacheService studentStatisticsCacheService) {
        this.studentStatisticsCacheService = studentStatisticsCacheService;
    }

    @GetMapping("/gender")
    public ResponseVO getStudentGenderStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentGenderStatistics());
    }

    @GetMapping("/province")
    public ResponseVO getStudentProvinceStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentProvinceStatistics());
    }

    @GetMapping("/city")
    public ResponseVO getStudentCityStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentCityStatistics());
    }

    @GetMapping("/division")
    public ResponseVO getStudentDivisionStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentDivisionStatistics());
    }

    @GetMapping("/contact")
    public ResponseVO getStudentContactStatusStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentContactStatusStatistics());
    }

    @GetMapping("/grade")
    public ResponseVO getStudentGradeStatistics() {
        return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentGradeStatistics());
    }
}
