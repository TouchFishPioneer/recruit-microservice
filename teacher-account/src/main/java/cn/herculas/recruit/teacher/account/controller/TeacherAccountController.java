package cn.herculas.recruit.teacher.account.controller;

import cn.herculas.recruit.teacher.account.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.account.data.FO.TeacherAccountFO;
import cn.herculas.recruit.teacher.account.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.account.exception.TeacherAccountException;
import cn.herculas.recruit.teacher.account.service.TeacherAccountService;
import cn.herculas.recruit.teacher.account.util.parser.TeacherAccountParser;
import cn.herculas.recruit.teacher.account.util.wrapper.ResultWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class TeacherAccountController {

    private final TeacherAccountService teacherAccountService;

    public TeacherAccountController(TeacherAccountService teacherAccountService) {
        this.teacherAccountService = teacherAccountService;
    }

    @PostMapping("/index")
    public ResponseVO createTeacherAccount(@Valid TeacherAccountFO teacherAccountFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        TeacherAccount teacherAccount = TeacherAccountParser.formParser(teacherAccountFO);

        try {
            TeacherAccount result = teacherAccountService.createTeacherAccount(teacherAccount);
            return ResultWrapper.success(TeacherAccountParser.viewParser(result));
        } catch (TeacherAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/index")
    public ResponseVO updateTeacherAccount(@Valid TeacherAccountFO teacherAccountFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        TeacherAccount teacherAccount = TeacherAccountParser.formParser(teacherAccountFO);
        if (teacherAccount.getTeacherPassword() != null)
            return ResultWrapper.error(HttpStatus.FORBIDDEN, "Invalid Parameters");

        try {
            TeacherAccount result = teacherAccountService.updateTeacherAccount(teacherAccount);
            return ResultWrapper.success(TeacherAccountParser.viewParser(result));
        } catch (TeacherAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/index/password")
    public ResponseVO updateTeacherAccountPassword(@RequestParam("old_password") String oldPassword,
                                                   @RequestParam("password") String newPassword,
                                                   @RequestParam("uuid") String teacherUuid) {

        TeacherAccount teacherAccount = teacherAccountService.findTeacherAccountByUuid(teacherUuid);
        if (!teacherAccount.getTeacherPassword().equals(oldPassword))
            return ResultWrapper.error(HttpStatus.FORBIDDEN, "Invalid Parameters");
        teacherAccount.setTeacherPassword(newPassword);
        try {
            teacherAccountService.updateTeacherAccount(teacherAccount);
            return ResultWrapper.success();
        } catch (TeacherAccountException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PostMapping("/confirm")
    public Map<String, String> confirmTeacherAccount(@RequestParam("username") String teacherUsername,
                                                     @RequestParam("password") String teacherPassword) {

        TeacherAccount teacherAccount = teacherAccountService.confirmTeacherAccount(teacherUsername, teacherPassword);
        if (teacherAccount == null) {
            return null;
        } else {
            Map<String, String> result = new HashMap<>();
            result.put("uuid", teacherAccount.getTeacherUuid());
            result.put("role", String.valueOf(teacherAccount.getTeacherRole()));
            result.put("region", teacherAccount.getTeacherRegion());
            return result;
        }
    }

    @GetMapping("/info")
    public ResponseVO findTeacherAccountByToken(HttpServletRequest httpServletRequest) {

        // TODO: This method should be cacheable

        TeacherAccount teacherAccount = teacherAccountService.findTeacherAccountByCookie(httpServletRequest);
        if (teacherAccount == null) {
            return ResultWrapper.error(HttpStatus.NOT_FOUND, "Teacher do not exist.");
        }
        return ResultWrapper.success(TeacherAccountParser.viewParser(teacherAccount));
    }
}
