package cn.herculas.recruit.student.information.service;

import cn.herculas.recruit.student.information.data.DTO.StudentContactStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentDivisionStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentGradeStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentRegionStatisticsDTO;

import java.util.List;

public interface VisualizationService {
    List<StudentRegionStatisticsDTO> countRegionStatistics();
    List<StudentDivisionStatisticsDTO> countDivisionStatistics();
    List<StudentContactStatisticsDTO> countContactStatistics();
    List<StudentGradeStatisticsDTO> countGradeStatistics();
}
