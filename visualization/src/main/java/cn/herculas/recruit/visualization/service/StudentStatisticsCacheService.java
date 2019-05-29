package cn.herculas.recruit.visualization.service;

import java.util.Map;

public interface StudentStatisticsCacheService {
    Map<Integer, Long> getCachedStudentGenderStatistics();
    Map<String, Long> getCachedStudentProvinceStatistics();
    Map<String, Long> getCachedStudentCityStatistics();
    Map<Integer, Long> getCachedStudentDivisionStatistics();
    Map<Integer, Long> getCachedStudentContactStatusStatistics();
    Map<Integer, Long> getCachedStudentGradeStatistics();
}
