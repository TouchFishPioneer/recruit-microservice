package cn.herculas.recruit.notification.repository;

import cn.herculas.recruit.notification.data.DO.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
