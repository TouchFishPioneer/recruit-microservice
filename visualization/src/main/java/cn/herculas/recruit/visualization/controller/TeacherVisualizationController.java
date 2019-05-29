package cn.herculas.recruit.visualization.controller;

import cn.herculas.recruit.visualization.data.VO.ResponseVO;
import cn.herculas.recruit.visualization.service.TeacherStatisticsCacheService;
import cn.herculas.recruit.visualization.util.wrapper.ResponseWrapper;
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
        return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherGenderStatistics());
    }

    @GetMapping("/department")
    public ResponseVO getTeacherDepartmentStatistics() {
        return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherDepartmentStatistics());
    }

    @GetMapping("/province")
    public ResponseVO getTeacherProvinceStatistics() {
        return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherProvinceStatistics());
    }

    @GetMapping("/city")
    public ResponseVO getTeacherCityStatistics() {
        return ResponseWrapper.success(teacherStatisticsCacheService.getCachedTeacherCityStatistics());
    }
}
