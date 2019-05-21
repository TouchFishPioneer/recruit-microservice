package cn.herculas.recruit.student.data.FO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class StudentLoginFO {
    @NotEmpty(message = "email cannot be empty.")
    private String email;
    @NotEmpty(message = "password cannot be empty.")
    private String password;
    @NotEmpty(message = "captcha key cannot be empty.")
    private String captcha_key;
    @NotEmpty(message = "captcha content cannot be empty.")
    private String captcha_content;
}
