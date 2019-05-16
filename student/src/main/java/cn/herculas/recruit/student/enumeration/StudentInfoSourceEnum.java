package cn.herculas.recruit.student.enumeration;

import lombok.Getter;

@Getter
public enum StudentInfoSourceEnum {
    IMPORT(0, "imported from local education authority"),
    REGISTER(1, "data from student self-registration"),
    IMPORT_AND_REGISTER(2, "student data have been confirmed");

    private Integer code;
    private String description;

    StudentInfoSourceEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
