package cn.herculas.recruit.teacher.account.service;

import cn.herculas.recruit.teacher.account.data.DO.TeacherAccount;

import javax.servlet.http.HttpServletRequest;

public interface TeacherAccountService {
    TeacherAccount confirmTeacherAccount(String teacherUsername, String teacherPassword);
    TeacherAccount createTeacherAccount(TeacherAccount teacherAccount);
    TeacherAccount updateTeacherAccount(TeacherAccount teacherAccount);
    TeacherAccount findTeacherAccountByUuid(String teacherUuid);
    TeacherAccount findTeacherAccountByCookie(HttpServletRequest httpServletRequest);
}
