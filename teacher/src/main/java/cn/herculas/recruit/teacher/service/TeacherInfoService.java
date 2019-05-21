package cn.herculas.recruit.teacher.service;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherInfoService {
    Page<TeacherInfo> listTeacherInfo(Pageable pageable);
    TeacherInfo findTeacherInfo(String teacherUuid);
    TeacherInfo createTeacherInfo(TeacherInfo teacherInfo);
    TeacherInfo updateTeacherInfo(TeacherInfo teacherInfo);
}
