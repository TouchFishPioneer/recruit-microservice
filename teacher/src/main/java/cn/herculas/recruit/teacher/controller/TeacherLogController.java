package cn.herculas.recruit.teacher.controller;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.data.FO.TeacherLoginFO;
import cn.herculas.recruit.teacher.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.service.CaptchaService;
import cn.herculas.recruit.teacher.service.CookieService;
import cn.herculas.recruit.teacher.service.SessionService;
import cn.herculas.recruit.teacher.service.TeacherAccountService;
import cn.herculas.recruit.teacher.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/log")
public class TeacherLogController {

    private final CaptchaService captchaService;
    private final CookieService cookieService;
    private final SessionService sessionService;
    private final TeacherAccountService teacherAccountService;

    public TeacherLogController(CaptchaService captchaService,
                                CookieService cookieService,
                                SessionService sessionService,
                                TeacherAccountService teacherAccountService) {
        this.captchaService = captchaService;
        this.cookieService = cookieService;
        this.sessionService = sessionService;
        this.teacherAccountService = teacherAccountService;
    }

    @PostMapping("/login")
    public ResponseVO studentLogin(@Valid TeacherLoginFO teacherLoginFO,
                                   BindingResult bindingResult,
                                   HttpServletResponse httpServletResponse) {

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        if (!captchaService.validateCaptcha(teacherLoginFO.getCaptcha_key(), teacherLoginFO.getCaptcha_content())) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, "captcha", "Captcha false.");
        }
        try {
            TeacherAccount teacherAccount = teacherAccountService.confirmTeacherAccount(
                    teacherLoginFO.getUsername(),
                    teacherLoginFO.getPassword());
            String sessionId = sessionService.generateSession(
                    teacherAccount.getTeacherUuid(),
                    String.valueOf(teacherAccount.getTeacherRole()),
                    "null");
            cookieService.generateCookie(httpServletResponse, sessionId);
            return ResponseWrapper.success();
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PostMapping("/logout")
    public ResponseVO studentLogout(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {

        Cookie cookie = cookieService.findCookie(httpServletRequest);
        if (cookie == null) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST,
                    "logout",
                    "Has already logged out.");
        }
        if (sessionService.deleteSession(cookie.getValue())) {
            cookieService.deleteCookie(httpServletResponse);
            return ResponseWrapper.success();
        } else {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR,
                    "session",
                    "Session deletion failed.");
        }
    }
}
