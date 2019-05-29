package cn.herculas.recruit.student.repository;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import cn.herculas.recruit.student.data.DTO.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    Page<StudentInfo> findAllByStudentRegionLike(String studentRegion, Pageable pageable);
    StudentInfo findByStudentIdentityNumber(String studentIdentityNumber);
    StudentInfo findByStudentAdmissionNumber(String studentAdmissionNumber);
    StudentInfo findByStudentUuid(String studentUuid);

    @Query("SELECT NEW cn.herculas.recruit.student.data.DTO.StudentGenderStatisticsDTO(si.studentGender, COUNT(si)) " +
            "FROM cn.herculas.recruit.student.data.DO.StudentInfo si " +
            "GROUP BY si.studentGender")
    List<StudentGenderStatisticsDTO> groupByStudentGender();
    @Query("SELECT NEW cn.herculas.recruit.student.data.DTO.StudentRegionStatisticsDTO(si.studentRegion, COUNT(si)) " +
            "FROM cn.herculas.recruit.student.data.DO.StudentInfo si " +
            "GROUP BY si.studentRegion")
    List<StudentRegionStatisticsDTO> groupByStudentRegion();
    @Query("SELECT NEW cn.herculas.recruit.student.data.DTO.StudentDivisionStatisticsDTO(si.studentDivision, COUNT(si)) " +
            "FROM cn.herculas.recruit.student.data.DO.StudentInfo si " +
            "GROUP BY si.studentDivision")
    List<StudentDivisionStatisticsDTO> groupByStudentDivision();
    @Query("SELECT NEW cn.herculas.recruit.student.data.DTO.StudentContactStatusStatisticsDTO(si.studentContactStatus, COUNT(si)) " +
            "FROM cn.herculas.recruit.student.data.DO.StudentInfo si " +
            "GROUP BY si.studentContactStatus")
    List<StudentContactStatusStatisticsDTO> groupByStudentContactStatus();
    @Query("SELECT NEW cn.herculas.recruit.student.data.DTO.StudentGradeStatisticsDTO(si.studentGrade, COUNT(si)) " +
            "FROM cn.herculas.recruit.student.data.DO.StudentInfo si " +
            "GROUP BY si.studentGrade")
    List<StudentGradeStatisticsDTO> groupByStudentGrade();
}
