package cn.herculas.recruit.notification.controller;

import cn.herculas.recruit.notification.data.DO.Notification;
import cn.herculas.recruit.notification.data.VO.NotificationVO;
import cn.herculas.recruit.notification.data.VO.ResponseVO;
import cn.herculas.recruit.notification.service.NotificationService;
import cn.herculas.recruit.notification.util.parser.NotificationParser;
import cn.herculas.recruit.notification.util.wrapper.ResponseWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/index")
    public ResponseVO listNotifications(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "20") Integer size) {

        Page<Notification> notificationPage = notificationService.listNotifications(PageRequest.of(page, size));
        List<NotificationVO> notificationVOList = new ArrayList<>();
        for (Notification notification : notificationPage) {
            notificationVOList.add(NotificationParser.viewParser(notification));
        }
        return ResponseWrapper.success(notificationVOList);
    }

    @PostMapping("/index")
    public ResponseVO createNotification(@Valid NotificationVO notificationVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, "Invalid parameters.");
        }
        Notification notification = NotificationParser.formParser(notificationVO);
        Notification result = notificationService.createNotification(notification);
        return ResponseWrapper.success(NotificationParser.viewParser(result));
    }
}
