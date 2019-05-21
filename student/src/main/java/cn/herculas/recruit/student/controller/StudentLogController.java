package cn.herculas.recruit.student.controller;

import cn.herculas.recruit.student.data.DO.StudentAccount;
import cn.herculas.recruit.student.data.FO.StudentLoginFO;
import cn.herculas.recruit.student.data.VO.ResponseVO;
import cn.herculas.recruit.student.enumeration.RoleEnum;
import cn.herculas.recruit.student.exception.StudentException;
import cn.herculas.recruit.student.service.CaptchaService;
import cn.herculas.recruit.student.service.CookieService;
import cn.herculas.recruit.student.service.SessionService;
import cn.herculas.recruit.student.service.StudentAccountService;
import cn.herculas.recruit.student.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class StudentLogController {

    private final CaptchaService captchaService;
    private final CookieService cookieService;
    private final SessionService sessionService;
    private final StudentAccountService studentAccountService;

    public StudentLogController(CaptchaService captchaService,
                                CookieService cookieService,
                                SessionService sessionService,
                                StudentAccountService studentAccountService) {
        this.captchaService = captchaService;
        this.cookieService = cookieService;
        this.sessionService = sessionService;
        this.studentAccountService = studentAccountService;
    }

    @PostMapping("/login")
    public ResponseVO studentLogin(@Valid StudentLoginFO studentLoginFO, BindingResult bindingResult,
                                   HttpServletResponse httpServletResponse) {

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        if (!captchaService.validateCaptcha(studentLoginFO.getCaptcha_key(), studentLoginFO.getCaptcha_content())) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, "captcha", "Captcha false.");
        }

        try {
            StudentAccount studentAccount = studentAccountService.confirmStudentAccount(
                    studentLoginFO.getEmail(),
                    studentLoginFO.getPassword());
            String sessionId = sessionService.generateSession(
                    studentAccount.getStudentUuid(),
                    String.valueOf(RoleEnum.STUDENT.getCode()),
                    "null");
            cookieService.generateCookie(httpServletResponse, sessionId);
            return ResponseWrapper.success();
        } catch (StudentException e) {
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
