package cn.herculas.recruit.student.information.repository;

import cn.herculas.recruit.student.information.data.DO.StudentInformation;
import cn.herculas.recruit.student.information.data.DTO.StudentContactStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentDivisionStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentGradeStatisticsDTO;
import cn.herculas.recruit.student.information.data.DTO.StudentRegionStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInformationRepository extends JpaRepository<StudentInformation, Integer> {
    StudentInformation findByStudentIdentityNumber(String studentIdentityNumber);
    StudentInformation findByStudentAdmissionNumber(String studentAdmissionNumber);
    StudentInformation findByStudentUuid(String studentUuid);

    @Query("select new cn.herculas.recruit.student.information.data.DTO.StudentRegionStatisticsDTO(si.studentRegion, count(si)) from StudentInformation si group by si.studentRegion")
    List<StudentRegionStatisticsDTO> groupByStudentRegion();
    @Query("select new cn.herculas.recruit.student.information.data.DTO.StudentDivisionStatisticsDTO(si.studentDivision, count(si)) from StudentInformation si group by si.studentDivision")
    List<StudentDivisionStatisticsDTO> groupByStudentDivision();
    @Query("select new cn.herculas.recruit.student.information.data.DTO.StudentContactStatisticsDTO(si.studentContactStatus, count(si)) from StudentInformation si group by si.studentContactStatus")
    List<StudentContactStatisticsDTO> groupByStudentContactStatus();
    @Query("select new cn.herculas.recruit.student.information.data.DTO.StudentGradeStatisticsDTO(si.studentGrade, count(si)) from StudentInformation si group by si.studentGrade")
    List<StudentGradeStatisticsDTO> groupByStudentGrade();
}
