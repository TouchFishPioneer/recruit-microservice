package cn.herculas.recruit.notification.util.parser;

import cn.herculas.recruit.notification.data.DO.Notification;
import cn.herculas.recruit.notification.data.VO.NotificationVO;
import cn.herculas.recruit.notification.enumeration.NotificationStatusEnum;

public class NotificationParser {
    public static Notification formParser(NotificationVO notificationVO) {
        Notification notification = new Notification();

        notification.setNotificationTeacherUuid(notificationVO.getTeacher_uuid());
        notification.setNotificationRegion(notificationVO.getRegion());
        notification.setNotificationTheme(notificationVO.getTheme());
        notification.setNotificationContent(notificationVO.getContent());

        if (notificationVO.getStatus() == null) {
            notification.setNotificationStatus(NotificationStatusEnum.SENDING.getCode());
        } else {
            notification.setNotificationStatus(notificationVO.getStatus());
        }

        notification.setNotificationUuid(notificationVO.getUuid());

        return notification;
    }

    public static NotificationVO viewParser(Notification notification) {
        NotificationVO notificationVO = new NotificationVO();

        notificationVO.setTeacher_uuid(notification.getNotificationTeacherUuid());
        notificationVO.setRegion(notification.getNotificationRegion());
        notificationVO.setTheme(notification.getNotificationTheme());
        notificationVO.setContent(notification.getNotificationContent());
        notificationVO.setStatus(notification.getNotificationStatus());
        notificationVO.setUuid(notification.getNotificationUuid());

        return notificationVO;
    }
}
