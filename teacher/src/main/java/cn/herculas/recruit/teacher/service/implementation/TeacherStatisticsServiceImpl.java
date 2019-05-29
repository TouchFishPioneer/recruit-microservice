package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.data.DTO.TeacherDepartmentStatisticsDTO;
import cn.herculas.recruit.teacher.data.DTO.TeacherGenderStatisticsDTO;
import cn.herculas.recruit.teacher.data.DTO.TeacherRegionStatisticsDTO;
import cn.herculas.recruit.teacher.repository.TeacherAccountRepository;
import cn.herculas.recruit.teacher.repository.TeacherInfoRepository;
import cn.herculas.recruit.teacher.service.TeacherStatisticsService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeacherStatisticsServiceImpl implements TeacherStatisticsService {

    private final TeacherAccountRepository teacherAccountRepository;
    private final TeacherInfoRepository teacherInfoRepository;

    public TeacherStatisticsServiceImpl(TeacherAccountRepository teacherAccountRepository,
                                        TeacherInfoRepository teacherInfoRepository) {
        this.teacherAccountRepository = teacherAccountRepository;
        this.teacherInfoRepository = teacherInfoRepository;
    }

    @Override
    public Map<Integer, Long> groupByGender() {
        return teacherInfoRepository
                .groupByTeacherGender()
                .parallelStream()
                .collect(Collectors.toMap(
                        TeacherGenderStatisticsDTO::getGender,
                        TeacherGenderStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<String, Long> groupByDepartment() {
        return teacherInfoRepository
                .groupByTeacherDepartment()
                .parallelStream()
                .collect(Collectors.toMap(
                        TeacherDepartmentStatisticsDTO::getDepartment,
                        TeacherDepartmentStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<String, Long> groupByProvince() {
        return teacherAccountRepository
                .groupByTeacherRegion()
                .parallelStream()
                .collect(Collectors.toMap(
                        TeacherRegionStatisticsDTO::getProvince,
                        TeacherRegionStatisticsDTO::getCount,
                        Long::sum));
    }

    @Override
    public Map<String, Long> groupByCity() {
        return teacherAccountRepository
                .groupByTeacherRegion()
                .parallelStream()
                .collect(Collectors.toMap(
                        TeacherRegionStatisticsDTO::getCity,
                        TeacherRegionStatisticsDTO::getCount,
                        Long::sum));
    }
}
