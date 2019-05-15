package cn.herculas.recruit.teacher.log.service;

import java.util.Map;

public interface TeacherAccountService {
    Map<String, String> validateTeacherAccount(String username, String password);
}
