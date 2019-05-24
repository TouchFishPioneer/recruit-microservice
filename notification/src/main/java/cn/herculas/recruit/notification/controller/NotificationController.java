package cn.herculas.recruit.notification.controller;

import cn.herculas.recruit.notification.data.DO.Notification;
import cn.herculas.recruit.notification.data.FO.NotificationFO;
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
import java.util.List;
import java.util.stream.Collectors;

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
        List<NotificationFO> notificationFOList = notificationPage.stream().map(NotificationParser::viewParser).collect(Collectors.toList());
        return ResponseWrapper.success(notificationFOList);
    }

    @PostMapping("/index")
    public ResponseVO createNotification(@Valid NotificationFO notificationFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        Notification notification = NotificationParser.formParser(notificationFO);
        Notification result = notificationService.createNotification(notification);
        return ResponseWrapper.success(NotificationParser.viewParser(result));
    }
}
