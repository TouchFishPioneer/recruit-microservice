package cn.herculas.recruit.teacher.service;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;

public interface TeacherAccountService {
    TeacherAccount findTeacherAccountByUuid(String teacherUuid);
    TeacherAccount createTeacherAccount(TeacherAccount teacherAccount);
    TeacherAccount updateTeacherAccount(TeacherAccount teacherAccount);
    TeacherAccount confirmTeacherAccount(String teacherUsername, String teacherPassword);
}
