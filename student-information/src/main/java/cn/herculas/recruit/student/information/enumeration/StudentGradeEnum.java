package cn.herculas.recruit.student.information.enumeration;

import lombok.Getter;

@Getter
public enum StudentGradeEnum {
    GRADE_TEN(1, "sophomore"),
    GRADE_ELEVEN(2, "junior"),
    GRADE_TWELVE(3, "senior");

    private Integer code;
    private String description;

    StudentGradeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
