package cn.herculas.recruit.teacher.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionStatusEnum {
    TEACHER_NOT_EXIST(10, "This teacher does not exist in database."),
    TEACHER_ALREADY_EXIST(11, "This teacher has already stored in database."),

    PASSWORD_ERROR(20, "The input password cannot match the account."),
    STATUS_ERROR(21, "The account has already been disabled."),

    SESSION_NOT_EXIST(30, "Session needed do not exist.");

    private Integer code;
    private String description;

    ExceptionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
