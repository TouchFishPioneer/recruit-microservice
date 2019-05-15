package cn.herculas.recruit.teacher.account.data.VO;

import lombok.Data;

@Data
public class TeacherAccountVO {
    private String username;
    private String nickname;
    private Integer role;
    private Integer status;
    private String region;
    private String avatar;
    private String uuid;
}
