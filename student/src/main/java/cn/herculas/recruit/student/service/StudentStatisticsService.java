package cn.herculas.recruit.student.service;

import java.util.Map;

public interface StudentStatisticsService {
    Map<Integer, Long> groupByGender();
    Map<String, Long> groupByProvince();
    Map<String, Long> groupByCity();
    Map<Integer, Long> groupByDivision();
    Map<Integer, Long> groupByContactStatus();
    Map<Integer, Long> groupByGrade();
}
