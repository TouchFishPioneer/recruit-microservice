package cn.herculas.recruit.teacher.repository;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import cn.herculas.recruit.teacher.data.DTO.TeacherDepartmentStatisticsDTO;
import cn.herculas.recruit.teacher.data.DTO.TeacherGenderStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
    TeacherInfo findByTeacherCardNumber(String teacherCardNumber);
    TeacherInfo findByTeacherUuid(String teacherUuid);

    @Query("SELECT NEW cn.herculas.recruit.teacher.data.DTO.TeacherGenderStatisticsDTO(ti.teacherGender, COUNT(ti)) " +
            "FROM cn.herculas.recruit.teacher.data.DO.TeacherInfo ti " +
            "GROUP BY ti.teacherGender")
    List<TeacherGenderStatisticsDTO> groupByTeacherGender();
    @Query("SELECT NEW cn.herculas.recruit.teacher.data.DTO.TeacherDepartmentStatisticsDTO(ti.teacherDepartment, COUNT(ti)) " +
            "FROM cn.herculas.recruit.teacher.data.DO.TeacherInfo ti " +
            "GROUP BY ti.teacherDepartment")
    List<TeacherDepartmentStatisticsDTO> groupByTeacherDepartment();
}
