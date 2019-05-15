package cn.herculas.recruit.notification.enumeration;

import lombok.Getter;

@Getter
public enum NotificationStatusEnum {
    SENDING(0, "The notification is sending."),
    SEND_SUCCESS(1, "The notification is successfully delivered"),
    SEND_FAILED(2, "The notification delivery failed.");

    private Integer code;
    private String description;

    NotificationStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
