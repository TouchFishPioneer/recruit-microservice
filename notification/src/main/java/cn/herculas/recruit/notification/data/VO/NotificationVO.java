package cn.herculas.recruit.notification.data.VO;

import lombok.Data;

@Data
public class NotificationVO {
    private String teacher_uuid;
    private String region;
    private String theme;
    private String content;
    private Integer status;
    private String uuid;
}
