package cn.herculas.recruit.visualization.controller;

import cn.herculas.recruit.visualization.data.VO.ResponseVO;
import cn.herculas.recruit.visualization.service.StudentStatisticsCacheService;
import cn.herculas.recruit.visualization.util.wrapper.ResponseWrapper;
import com.alibaba.fastjson.JSONException;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentGenderStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

    }

    @GetMapping("/province")
    public ResponseVO getStudentProvinceStatistics() {
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentProvinceStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/city")
    public ResponseVO getStudentCityStatistics() {
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentCityStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/division")
    public ResponseVO getStudentDivisionStatistics() {
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentDivisionStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

    }

    @GetMapping("/contact")
    public ResponseVO getStudentContactStatusStatistics() {
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentContactStatusStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

    }

    @GetMapping("/grade")
    public ResponseVO getStudentGradeStatistics() {
        try {
            return ResponseWrapper.success(studentStatisticsCacheService.getCachedStudentGradeStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }

    }
}
