package cn.herculas.recruit.student.information.enumeration;

import lombok.Getter;

@Getter
public enum StudentContactStatusEnum {
    NOT_CONTACTED(0, "have not been reached"),
    CONTACTING(1, "under negotiating"),
    CONTACTED_NEGATIVE(2, "have been reached and confirm not coming"),
    CONTACTED_POSITIVE(3, "have been reached and signed the contract");

    private Integer code;
    private String description;

    StudentContactStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
