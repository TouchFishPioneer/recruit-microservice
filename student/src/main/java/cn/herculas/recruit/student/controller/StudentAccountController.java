package cn.herculas.recruit.student.controller;

import cn.herculas.recruit.student.data.DO.StudentAccount;
import cn.herculas.recruit.student.data.FO.StudentAccountFO;
import cn.herculas.recruit.student.data.VO.ResponseVO;
import cn.herculas.recruit.student.exception.StudentException;
import cn.herculas.recruit.student.service.StudentAccountService;
import cn.herculas.recruit.student.util.parser.StudentAccountParser;
import cn.herculas.recruit.student.util.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class StudentAccountController {

    private final StudentAccountService studentAccountService;

    public StudentAccountController(StudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    @GetMapping("/index")
    public ResponseVO getStudentAccount(@RequestParam("uuid") String studentUuid) {

        // TODO: Permission check

        try {
            StudentAccount result = studentAccountService.findStudentAccount(studentUuid);
            return ResponseWrapper.success(StudentAccountParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, e);
        }
    }

    @PostMapping("/index")
    public ResponseVO createStudentAccount(@Valid StudentAccountFO studentAccountFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        StudentAccount studentAccount = StudentAccountParser.formParser(studentAccountFO);
        try {
            StudentAccount result = studentAccountService.createStudentAccount(studentAccount);
            return ResponseWrapper.success(StudentAccountParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/detail")
    public ResponseVO updateStudentAccount(@Valid StudentAccountFO studentAccountFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        if (studentAccountFO.getPassword() != null) {
            return ResponseWrapper.error(
                    HttpStatus.BAD_REQUEST,
                    "password",
                    "You can not update password by this interface.");
        }
        StudentAccount studentAccount = StudentAccountParser.formParser(studentAccountFO);
        try {
            StudentAccount result = studentAccountService.updateStudentAccount(studentAccount);
            return ResponseWrapper.success(StudentAccountParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/password")
    public ResponseVO updateStudentPassword(@RequestParam("old_password") String oldPassword,
                                            @RequestParam("password") String newPassword,
                                            @RequestParam("uuid") String studentUuid) {

        // TODO: Permission check

        StudentAccount studentAccount;
        try {
            studentAccount = studentAccountService.findStudentAccount(studentUuid);
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, e);
        }
        if (!studentAccount.getStudentPassword().equals(oldPassword)) {
            return ResponseWrapper.error(
                    HttpStatus.BAD_REQUEST,
                    "password",
                    "Old password not correct.");
        }
        studentAccount.setStudentPassword(newPassword);
        try {
            studentAccountService.updateStudentAccount(studentAccount);
            return ResponseWrapper.success();
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }
}
