package cn.herculas.recruit.student.account.enumeration;

import lombok.Getter;

@Getter
public enum AccountStatusEnum {
    DISABLED(0, "This account is disabled."),
    VALID(1, "This account is valid.");

    private Integer code;
    private String description;

    AccountStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
