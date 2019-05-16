package cn.herculas.recruit.student.data.FO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class StudentAccountFO {
    @Email(message = "Not a pattern of an email.")
    private String email;
    private String password;
    @Size(min = 8, max = 16, message = "The length of nickname should be the range of 8 to 16.")
    private String nickname;
    private Integer status;
    private String uuid;
}
