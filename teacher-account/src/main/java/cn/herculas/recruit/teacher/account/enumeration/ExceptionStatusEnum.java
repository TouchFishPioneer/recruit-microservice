package cn.herculas.recruit.teacher.account.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionStatusEnum {
    TEACHER_NOT_EXIST(10, "This teacher does not exist in database."),
    TEACHER_ALREADY_EXIST(11, "This teacher has already stored in database."),

    SESSION_NOT_EXIST(20, "Session needed do not exist.");

    private Integer code;
    private String description;

    ExceptionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
