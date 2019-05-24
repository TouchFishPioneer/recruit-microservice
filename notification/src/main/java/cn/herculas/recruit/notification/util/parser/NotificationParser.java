package cn.herculas.recruit.notification.util.parser;

import cn.herculas.recruit.notification.data.DO.Notification;
import cn.herculas.recruit.notification.data.FO.NotificationFO;
import cn.herculas.recruit.notification.enumeration.NotificationStatusEnum;

public class NotificationParser {
    public static Notification formParser(NotificationFO notificationFO) {
        Notification notification = new Notification();

        notification.setNotificationTeacherUuid(notificationFO.getTeacher_uuid());
        notification.setNotificationRegion(notificationFO.getRegion());
        notification.setNotificationTheme(notificationFO.getTheme());
        notification.setNotificationContent(notificationFO.getContent());

        if (notificationFO.getStatus() == null) {
            notification.setNotificationStatus(NotificationStatusEnum.SENDING.getCode());
        } else {
            notification.setNotificationStatus(notificationFO.getStatus());
        }

        notification.setNotificationUuid(notificationFO.getUuid());

        return notification;
    }

    public static NotificationFO viewParser(Notification notification) {
        NotificationFO notificationFO = new NotificationFO();

        notificationFO.setTeacher_uuid(notification.getNotificationTeacherUuid());
        notificationFO.setRegion(notification.getNotificationRegion());
        notificationFO.setTheme(notification.getNotificationTheme());
        notificationFO.setContent(notification.getNotificationContent());
        notificationFO.setStatus(notification.getNotificationStatus());
        notificationFO.setUuid(notification.getNotificationUuid());

        return notificationFO;
    }
}
