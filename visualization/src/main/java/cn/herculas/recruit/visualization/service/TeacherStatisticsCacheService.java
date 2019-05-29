package cn.herculas.recruit.visualization.service;

import java.util.Map;

public interface TeacherStatisticsCacheService {
    Map<Integer, Long> getCachedTeacherGenderStatistics();
    Map<String, Long> getCachedTeacherDepartmentStatistics();
    Map<String, Long> getCachedTeacherProvinceStatistics();
    Map<String, Long> getCachedTeacherCityStatistics();
}
