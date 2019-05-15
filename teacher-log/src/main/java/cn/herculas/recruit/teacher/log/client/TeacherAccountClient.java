package cn.herculas.recruit.teacher.log.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "teacher-account")
public interface TeacherAccountClient {
    @PostMapping("/account/confirm")
    Map<String, String> confirmTeacherAccount(@RequestParam("username") String teacherUsername,
                                              @RequestParam("password") String teacherPassword);
}
