package cn.herculas.recruit.teacher.information.enumeration;

import lombok.Getter;

@Getter
public enum TeacherRoleEnum {
    ADMINISTRATOR(0, "super administrator"),
    ADMISSION_TEAM_LEADER(1, "admission team leader"),
    ADMISSION_TEAM_MEMBER(2, "simple admission team member");

    private Integer code;
    private String description;

    TeacherRoleEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
