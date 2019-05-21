package cn.herculas.recruit.teacher.controller;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.data.FO.TeacherAccountFO;
import cn.herculas.recruit.teacher.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.service.CookieService;
import cn.herculas.recruit.teacher.service.SessionService;
import cn.herculas.recruit.teacher.service.TeacherAccountService;
import cn.herculas.recruit.teacher.util.parser.TeacherAccountParser;
import cn.herculas.recruit.teacher.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class TeacherAccountController {

    private final CookieService cookieService;
    private final SessionService sessionService;
    private final TeacherAccountService teacherAccountService;

    public TeacherAccountController(TeacherAccountService teacherAccountService,
                                    CookieService cookieService,
                                    SessionService sessionService) {
        this.teacherAccountService = teacherAccountService;
        this.cookieService = cookieService;
        this.sessionService = sessionService;
    }

    @GetMapping("/index")
    public ResponseVO getTeacherAccount(HttpServletRequest httpServletRequest) {

        // TODO: Permission check

        Cookie cookie = cookieService.findCookie(httpServletRequest);
        if (cookie == null) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND,
                    "cookie",
                    "Cannot find valid cookie.");
        }
        Map<String, String> sessionContent = sessionService.findSession(cookie.getValue());
        if (sessionContent == null || !sessionContent.containsKey("uuid")) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND,
                    "session",
                    "Cannot find valid session.");
        }
        TeacherAccount teacherAccount = teacherAccountService.findTeacherAccountByUuid(sessionContent.get("uuid"));
        if (teacherAccount == null) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND,
                    "existence",
                    "Teacher do not exist.");
        }
        return ResponseWrapper.success(TeacherAccountParser.viewParser(teacherAccount));
    }

    @PostMapping("/index")
    public ResponseVO createTeacherAccount(@Valid TeacherAccountFO teacherAccountFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        TeacherAccount teacherAccount = TeacherAccountParser.formParser(teacherAccountFO);
        try {
            TeacherAccount result = teacherAccountService.createTeacherAccount(teacherAccount);
            return ResponseWrapper.success(TeacherAccountParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/detail")
    public ResponseVO updateTeacherAccount(@Valid TeacherAccountFO teacherAccountFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        TeacherAccount teacherAccount = TeacherAccountParser.formParser(teacherAccountFO);
        if (teacherAccount.getTeacherPassword() != null)
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST,
                    "password",
                    "You can not update password by this interface.");
        try {
            TeacherAccount result = teacherAccountService.updateTeacherAccount(teacherAccount);
            return ResponseWrapper.success(TeacherAccountParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/password")
    public ResponseVO updateTeacherAccountPassword(@RequestParam("old_password") String oldPassword,
                                                   @RequestParam("password") String newPassword,
                                                   @RequestParam("uuid") String teacherUuid) {

        // TODO: Permission check

        TeacherAccount teacherAccount;
        try {
            teacherAccount = teacherAccountService.findTeacherAccountByUuid(teacherUuid);
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, e);
        }

        if (!teacherAccount.getTeacherPassword().equals(oldPassword)) {
            return ResponseWrapper.error(
                    HttpStatus.FORBIDDEN,
                    "password",
                    "Old password not correct.");
        }
        teacherAccount.setTeacherPassword(newPassword);
        try {
            teacherAccountService.updateTeacherAccount(teacherAccount);
            return ResponseWrapper.success();
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }
}
