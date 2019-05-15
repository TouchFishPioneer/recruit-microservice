package cn.herculas.recruit.student.account.controller;

import cn.herculas.recruit.student.account.data.DO.StudentAccount;
import cn.herculas.recruit.student.account.data.FO.StudentAccountFO;
import cn.herculas.recruit.student.account.data.VO.ResultVO;
import cn.herculas.recruit.student.account.exception.StudentAccountException;
import cn.herculas.recruit.student.account.service.StudentAccountService;
import cn.herculas.recruit.student.account.util.parser.StudentAccountParser;
import cn.herculas.recruit.student.account.util.wrapper.ResultWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class StudentAccountController {

    private final StudentAccountService studentRegistrationService;

    public StudentAccountController(StudentAccountService studentRegistrationService) {
        this.studentRegistrationService = studentRegistrationService;
    }

    @PostMapping("/index")
    public ResultVO createStudentAccount(@Valid StudentAccountFO studentAccountFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        StudentAccount studentAccount = StudentAccountParser.formParser(studentAccountFO);

        try {
            StudentAccount result = studentRegistrationService.createStudentAccount(studentAccount);
            return ResultWrapper.success(StudentAccountParser.viewParser(result));
        } catch (StudentAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/detail")
    public ResultVO updateStudentAccount(@Valid StudentAccountFO studentAccountFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        StudentAccount studentAccount = StudentAccountParser.formParser(studentAccountFO);
        if (studentAccount.getStudentPassword() != null)
            return ResultWrapper.error(HttpStatus.FORBIDDEN, "Invalid Parameters");

        try {
            StudentAccount result = studentRegistrationService.updateStudentAccount(studentAccount);
            return ResultWrapper.success(StudentAccountParser.viewParser(result));
        } catch (StudentAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/password")
    public ResultVO updateStudentAccountPassword(@RequestParam("old_password") String oldPassword,
                                                 @RequestParam("password") String newPassword,
                                                 @RequestParam("uuid") String studentUuid) {

        StudentAccount studentAccount = studentRegistrationService.findStudentAccountByUuid(studentUuid);
        if (!studentAccount.getStudentPassword().equals(oldPassword))
            return ResultWrapper.error(HttpStatus.FORBIDDEN, "Invalid Parameters");
        studentAccount.setStudentPassword(newPassword);

        try {
            studentRegistrationService.updateStudentAccount(studentAccount);
            return ResultWrapper.success(null);
        } catch (StudentAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
