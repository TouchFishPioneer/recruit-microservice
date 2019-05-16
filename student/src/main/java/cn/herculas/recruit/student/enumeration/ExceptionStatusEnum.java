package cn.herculas.recruit.student.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionStatusEnum {
    STUDENT_NOT_EXIST(0, "This student does not exist in database."),
    STUDENT_ALREADY_EXIST(1, "This student has already been in database."),
    INFO_SOURCE_ERROR(10, "Wrong info source.");

    private Integer code;
    private String description;

    ExceptionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
