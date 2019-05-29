package cn.herculas.recruit.teacher.controller;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.data.FO.TeacherAccountFO;
import cn.herculas.recruit.teacher.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.service.TeacherAccountService;
import cn.herculas.recruit.teacher.util.parser.TeacherAccountParser;
import cn.herculas.recruit.teacher.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class TeacherAccountController {
    private final TeacherAccountService teacherAccountService;

    public TeacherAccountController(TeacherAccountService teacherAccountService) {
        this.teacherAccountService = teacherAccountService;
    }

    @GetMapping("/index")
    public ResponseVO getTeacherAccount(@RequestParam("uuid") String teacherUuid) {

        // TODO: Permission check implemented by Zuul Gateway

        try {
            TeacherAccount result = teacherAccountService.findTeacherAccount(teacherUuid);
            return ResponseWrapper.success(TeacherAccountParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, e);
        }
    }

    @PostMapping("/index")
    public ResponseVO createTeacherAccount(@Valid TeacherAccountFO teacherAccountFO, BindingResult bindingResult) {
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

        // TODO: Permission check implemented by Zuul Gateway

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

        // TODO: Permission check implemented by Zuul Gateway

        TeacherAccount teacherAccount;
        try {
            teacherAccount = teacherAccountService.findTeacherAccount(teacherUuid);
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
