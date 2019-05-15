package cn.herculas.recruit.teacher.log.controller;

import cn.herculas.recruit.teacher.log.data.FO.TeacherLogFO;
import cn.herculas.recruit.teacher.log.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.log.service.CaptchaService;
import cn.herculas.recruit.teacher.log.service.CookieService;
import cn.herculas.recruit.teacher.log.service.SessionService;
import cn.herculas.recruit.teacher.log.service.TeacherAccountService;
import cn.herculas.recruit.teacher.log.util.wrapper.ResponseWrapper;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RefreshScope
public class TeacherLogController {

    private final CaptchaService captchaService;
    private final CookieService cookieService;
    private final SessionService sessionService;
    private final TeacherAccountService teacherAccountService;

    public TeacherLogController(
            CaptchaService captchaService,
            CookieService cookieService,
            SessionService sessionService,
            TeacherAccountService teacherAccountService) {

        this.captchaService = captchaService;
        this.cookieService = cookieService;
        this.sessionService = sessionService;
        this.teacherAccountService = teacherAccountService;
    }


    @PostMapping("/in")
    public ResponseVO login(@Valid TeacherLogFO teacherLogFO,
                            BindingResult bindingResult,
                            HttpServletResponse httpServletResponse) {

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, "Invalid parameters.");
        }
        if (!captchaService.validateCaptcha(teacherLogFO.getCaptcha_key(), teacherLogFO.getCaptcha_content())) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, "Captcha false.");
        }
        Map<String, String> teacherParameters =
                teacherAccountService.validateTeacherAccount(teacherLogFO.getUsername(), teacherLogFO.getPassword());
        if (teacherParameters == null) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, "Username and password do not match.");
        }
        String sessionId = sessionService.generateTeacherSession(
                teacherParameters.get("uuid"),
                teacherParameters.get("role"),
                teacherParameters.get("region"));
        cookieService.generateCookie(httpServletResponse, sessionId);
        return ResponseWrapper.success();
    }

    @PostMapping("/out")
    public ResponseVO logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        Cookie cookie = cookieService.findCookie(httpServletRequest);
        if (cookie == null) {
            return ResponseWrapper.success();
        }
        cookieService.deleteCookie(httpServletResponse);
        if (sessionService.deleteTeacherSession(cookie.getValue())) {
            return ResponseWrapper.success();
        } else {
            return ResponseWrapper.error(HttpStatus.INTERNAL_SERVER_ERROR, "Session deletion failed.");
        }
    }
}
