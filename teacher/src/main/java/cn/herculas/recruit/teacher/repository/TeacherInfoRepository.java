package cn.herculas.recruit.teacher.repository;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherInfoRepository extends JpaRepository<TeacherInfo, Integer> {
    TeacherInfo findByTeacherUuid(String teacherUuid);
    TeacherInfo findByTeacherCardNumber(String teacherCardNumber);
}
