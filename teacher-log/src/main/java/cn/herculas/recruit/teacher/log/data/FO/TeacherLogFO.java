package cn.herculas.recruit.teacher.log.data.FO;

import lombok.Data;

@Data
public class TeacherLogFO {
    private String username;
    private String password;
    private String captcha_key;
    private String captcha_content;
}
