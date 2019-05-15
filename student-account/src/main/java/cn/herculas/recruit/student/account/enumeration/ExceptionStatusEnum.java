package cn.herculas.recruit.student.account.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionStatusEnum {
    STUDENT_NOT_EXIST(0, "This student does not exist in database."),
    STUDENT_ALREADY_EXIST(1, "This student has already been in database."),

    TEACHER_NOT_EXIST(10, "This teacher does not exist in database."),
    TEACHER_ALREADY_EXIST(11, "This teacher has already stored in database."),

    QUESTION_NOT_EXIST(20, "This question does not exist in database.");

    private Integer code;
    private String description;

    ExceptionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
