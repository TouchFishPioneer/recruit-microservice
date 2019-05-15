package cn.herculas.recruit.notification.service.implementation;

import cn.herculas.recruit.notification.data.DO.Notification;
import cn.herculas.recruit.notification.repository.NotificationRepository;
import cn.herculas.recruit.notification.service.NotificationService;
import cn.herculas.recruit.notification.util.generator.KeyGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Page<Notification> listNotifications(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Notification createNotification(Notification notification) {
        if (notification.getNotificationUuid() == null) {
            notification.setNotificationUuid(KeyGenerator.uuidGenerator());
        }

        // TODO: Deliver the notification by email or SMS

        return notificationRepository.save(notification);
    }
}
