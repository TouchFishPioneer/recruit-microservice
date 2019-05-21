package cn.herculas.recruit.teacher.data.FO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TeacherLoginFO {
    @NotEmpty(message = "username cannot be empty.")
    private String username;
    @NotEmpty(message = "password cannot be empty.")
    private String password;
    @NotEmpty(message = "captcha key cannot be empty.")
    private String captcha_key;
    @NotEmpty(message = "captcha content cannot be empty.")
    private String captcha_content;
}
