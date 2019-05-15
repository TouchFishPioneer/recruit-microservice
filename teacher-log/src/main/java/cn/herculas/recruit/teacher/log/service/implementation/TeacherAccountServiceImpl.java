package cn.herculas.recruit.teacher.log.service.implementation;

import cn.herculas.recruit.teacher.log.client.TeacherAccountClient;
import cn.herculas.recruit.teacher.log.service.TeacherAccountService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TeacherAccountServiceImpl implements TeacherAccountService {

    private final TeacherAccountClient teacherAccountClient;

    public TeacherAccountServiceImpl(TeacherAccountClient teacherAccountClient) {
        this.teacherAccountClient = teacherAccountClient;
    }

    @Override
    public Map<String, String> validateTeacherAccount(String username, String password) {
        return teacherAccountClient.confirmTeacherAccount(username, password);
    }
}
