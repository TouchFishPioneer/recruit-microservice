package cn.herculas.recruit.student.account.data.FO;

import lombok.Data;

@Data
public class StudentAccountFO {
    private String email;
    private String password;
    private String nickname;
    private Integer status;
    private String uuid;
}
