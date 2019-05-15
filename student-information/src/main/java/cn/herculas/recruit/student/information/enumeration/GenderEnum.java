package cn.herculas.recruit.student.information.enumeration;

import lombok.Getter;

@Getter
public enum GenderEnum {
    FEMALE(0, "female"),
    MALE(1, "male");

    private Integer code;
    private String description;

    GenderEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
