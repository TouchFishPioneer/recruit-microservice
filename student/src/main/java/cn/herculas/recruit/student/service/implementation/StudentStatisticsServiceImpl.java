package cn.herculas.recruit.student.service.implementation;

import cn.herculas.recruit.student.data.DTO.*;
import cn.herculas.recruit.student.repository.StudentInfoRepository;
import cn.herculas.recruit.student.service.StudentStatisticsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentStatisticsServiceImpl implements StudentStatisticsService {

    private final StudentInfoRepository studentInfoRepository;

    public StudentStatisticsServiceImpl(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public Map<Integer, Long> groupByGender() {
        return studentInfoRepository
                .groupByStudentGender()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentGenderStatisticsDTO::getGender,
                        StudentGenderStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<String, Long> groupByProvince() {
        return studentInfoRepository
                .groupByStudentRegion()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentRegionStatisticsDTO::getProvince,
                        StudentRegionStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<String, Long> groupByCity() {
        return studentInfoRepository
                .groupByStudentRegion()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentRegionStatisticsDTO::getCity,
                        StudentRegionStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<Integer, Long> groupByDivision() {
        return studentInfoRepository
                .groupByStudentDivision()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentDivisionStatisticsDTO::getDivision,
                        StudentDivisionStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<Integer, Long> groupByContactStatus() {
        return studentInfoRepository
                .groupByStudentContactStatus()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentContactStatusStatisticsDTO::getContactStatus,
                        StudentContactStatusStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<Integer, Long> groupByGrade() {
        return studentInfoRepository
                .groupByStudentGrade()
                .parallelStream()
                .collect(Collectors.toMap(
                        StudentGradeStatisticsDTO::getGrade,
                        StudentGradeStatisticsDTO::getCount,
                        Long::sum));
    }
}
