package cn.herculas.recruit.teacher.data.FO;

import lombok.Data;

@Data
public class TeacherAccountFO {
    private String username;
    private String password;
    private String nickname;
    private Integer role;
    private Integer status;
    private String region;
    private String avatar;
    private String uuid;
}
