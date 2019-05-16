package cn.herculas.recruit.student.repository;

import cn.herculas.recruit.student.data.DO.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAccountRepository extends JpaRepository<StudentAccount, Integer> {
    StudentAccount findByStudentUuid(String studentUuid);
    StudentAccount findByStudentEmail(String studentEmail);
}
