package cn.herculas.recruit.student.repository;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer> {
    StudentInfo findByStudentIdentityNumber(String studentIdentityNumber);
    StudentInfo findByStudentAdmissionNumber(String studentAdmissionNumber);
    StudentInfo findByStudentUuid(String studentUuid);
}
