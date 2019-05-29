package cn.herculas.recruit.teacher.service;

import java.util.Map;

public interface TeacherStatisticsService {
    Map<Integer, Long> groupByGender();
    Map<String, Long> groupByDepartment();
    Map<String, Long> groupByProvince();
    Map<String, Long> groupByCity();
}
