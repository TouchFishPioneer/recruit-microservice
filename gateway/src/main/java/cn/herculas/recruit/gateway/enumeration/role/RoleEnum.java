package cn.herculas.recruit.gateway.enumeration.role;

import lombok.Getter;

@Getter
public enum RoleEnum {
    STUDENT(0, "student"),
    TEACHER_ADMINISTRATOR(1, "super administrator"),
    TEACHER_ADMISSION_TEAM_LEADER(2, "admission team leader"),
    TEACHER_ADMISSION_TEAM_MEMBER(3, "simple admission team member");

    private Integer code;
    private String description;

    RoleEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
