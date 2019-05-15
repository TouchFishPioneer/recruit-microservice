package cn.herculas.recruit.notification.service;

import cn.herculas.recruit.notification.data.DO.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NotificationService {
    Page<Notification> listNotifications(Pageable pageable);
    Notification createNotification(Notification notification);
}
