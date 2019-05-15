package cn.herculas.recruit.teacher.information.repository;

import cn.herculas.recruit.teacher.information.data.DO.TeacherInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherInformationRepository extends JpaRepository<TeacherInformation, Integer> {
    TeacherInformation findByTeacherUuid(String teacherUuid);
    TeacherInformation findByTeacherCardNumber(String teacherCardNumber);
}
