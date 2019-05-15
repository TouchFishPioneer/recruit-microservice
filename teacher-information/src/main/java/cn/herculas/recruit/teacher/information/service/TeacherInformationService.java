package cn.herculas.recruit.teacher.information.service;

import cn.herculas.recruit.teacher.information.data.DO.TeacherInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherInformationService {
    TeacherInformation createTeacherInformation(TeacherInformation teacherInformation);
    TeacherInformation updateTeacherInformation(TeacherInformation teacherInformation);
    Page<TeacherInformation> listTeacherInformation(Pageable pageable);
    TeacherInformation findTeacherInformation(String teacherUuid);
}
