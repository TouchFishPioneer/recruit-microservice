package cn.herculas.recruit.notification.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationId;
    private String notificationTeacherUuid;
    private String notificationRegion;
    private String notificationTheme;
    private String notificationContent;
    private Integer notificationStatus;
    private String notificationUuid;
}
