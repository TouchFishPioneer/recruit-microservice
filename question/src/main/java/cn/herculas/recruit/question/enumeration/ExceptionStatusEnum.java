package cn.herculas.recruit.question.enumeration;

import lombok.Getter;

@Getter
public enum ExceptionStatusEnum {
    QUESTION_NOT_EXIST(20, "This question does not exist in database.");

    private Integer code;
    private String description;

    ExceptionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
