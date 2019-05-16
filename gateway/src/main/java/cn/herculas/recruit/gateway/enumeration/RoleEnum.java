package cn.herculas.recruit.gateway.enumeration;

import lombok.Getter;

@Getter
public enum RoleEnum {
    NO_DEMAND(0, "No role demand."),
    STUDENT(1, "student"),
    TEACHER_ADMINISTRATOR(2, "super administrator"),
    TEACHER_ADMISSION_TEAM_LEADER(3, "admission team leader"),
    TEACHER_ADMISSION_TEAM_MEMBER(4, "simple admission team member");

    private Integer code;
    private String description;

    RoleEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
