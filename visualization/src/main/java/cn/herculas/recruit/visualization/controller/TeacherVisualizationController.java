package cn.herculas.recruit.visualization.controller;

import cn.herculas.recruit.visualization.data.VO.ResponseVO;
import cn.herculas.recruit.visualization.service.TeacherStatisticsCacheService;
import cn.herculas.recruit.visualization.util.wrapper.ResponseWrapper;
import com.alibaba.fastjson.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherVisualizationController {

    private final TeacherStatisticsCacheService teacherStatisticsCacheService;

    public TeacherVisualizationController(TeacherStatisticsCacheService teacherStatisticsCacheService) {
        this.teacherStatisticsCacheService = teacherStatisticsCacheService;
    }

    @GetMapping("/gender")
    public ResponseVO getTeacherGenderStatistics() {
        try {
            return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherGenderStatistics());
            } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/department")
    public ResponseVO getTeacherDepartmentStatistics() {
        try {
            return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherDepartmentStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/province")
    public ResponseVO getTeacherProvinceStatistics() {
        try {
            return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherProvinceStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @GetMapping("/city")
    public ResponseVO getTeacherCityStatistics() {
        try {
            return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherCityStatistics());
        } catch (JSONException e) {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
