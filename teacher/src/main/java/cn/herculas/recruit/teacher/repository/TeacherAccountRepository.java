package cn.herculas.recruit.teacher.repository;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherAccountRepository extends JpaRepository<TeacherAccount, Integer> {
    TeacherAccount findByTeacherUsername(String teacherUsername);
    TeacherAccount findByTeacherUuid(String teacherUuid);
}
