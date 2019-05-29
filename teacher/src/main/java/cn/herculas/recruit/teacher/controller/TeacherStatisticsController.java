package cn.herculas.recruit.teacher.controller;

import cn.herculas.recruit.teacher.service.TeacherStatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class TeacherStatisticsController {

    private final TeacherStatisticsService teacherStatisticsService;

    public TeacherStatisticsController(TeacherStatisticsService teacherStatisticsService) {
        this.teacherStatisticsService = teacherStatisticsService;
    }

    @GetMapping("/gender")
    public Map<Integer, Long> getTeacherGenderStatistics() {
        return teacherStatisticsService.groupByGender();
    }

    @GetMapping("/department")
    public Map<String, Long> getTeacherDepartmentStatistics() {
        return teacherStatisticsService.groupByDepartment();
    }

    @GetMapping("/province")
    public Map<String, Long> getTeacherProvinceStatistics() {
        return teacherStatisticsService.groupByProvince();
    }

    @GetMapping("/city")
    public Map<String, Long> getTeacherCityStatistics() {
        return teacherStatisticsService.groupByCity();
    }
}
