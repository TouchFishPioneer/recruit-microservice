package cn.herculas.recruit.notification.data.FO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NotificationFO {
    @NotEmpty(message = "The uuid of teacher cannot be empty.")
    private String teacher_uuid;
    @NotEmpty(message = "The region of notification cannot be empty.")
    private String region;
    private String theme;
    private String content;
    private Integer status;
    private String uuid;
}
