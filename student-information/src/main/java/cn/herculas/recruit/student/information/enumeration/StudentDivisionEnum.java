package cn.herculas.recruit.student.information.enumeration;

import lombok.Getter;

@Getter
public enum StudentDivisionEnum {
    LIBERAL(0, "liberal"),
    SCIENCE(1, "science"),
    ARTS(2, "arts"),
    SPORTS(3, "sports and physical");

    private Integer code;
    private String description;

    StudentDivisionEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
