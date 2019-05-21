package cn.herculas.recruit.student.controller;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import cn.herculas.recruit.student.data.FO.StudentInfoFO;
import cn.herculas.recruit.student.data.VO.ResponseVO;
import cn.herculas.recruit.student.data.VO.StudentInfoVO;
import cn.herculas.recruit.student.exception.StudentException;
import cn.herculas.recruit.student.service.StudentInfoService;
import cn.herculas.recruit.student.util.parser.StudentInfoParser;
import cn.herculas.recruit.student.util.wrapper.ResponseWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/info")
public class StudentInfoController {

    private final StudentInfoService studentInfoService;

    public StudentInfoController(StudentInfoService studentInfoService) {
        this.studentInfoService = studentInfoService;
    }

    @GetMapping("/list")
    public ResponseVO listStudentInfo(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "20") Integer size) {

        // TODO: Permission check

        Page<StudentInfo> studentInfoPage = studentInfoService.listStudentInfo(PageRequest.of(page, size));
        List<StudentInfoVO> studentInfoVOList = studentInfoPage.stream().map(StudentInfoParser::viewParser).collect(Collectors.toList());
        return ResponseWrapper.success(studentInfoVOList);
    }

    @GetMapping("/index/{uuid}")
    public ResponseVO findStudentInfo(@PathVariable(value = "uuid") String studentUuid) {

        // TODO: Permission check

        try {
            StudentInfo result = studentInfoService.findStudentInfo(studentUuid);
            return ResponseWrapper.success(StudentInfoParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND, e);
        }
    }

    @PostMapping("/index")
    public ResponseVO createStudentInfo(@Valid StudentInfoFO studentInfoFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        StudentInfo studentInfo = StudentInfoParser.formParser(studentInfoFO);
        try {
            StudentInfo result = studentInfoService.createStudentInfo(studentInfo);
            return ResponseWrapper.success(StudentInfoParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/index")
    public ResponseVO updateStudentInfo(@Valid StudentInfoFO studentInfoFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        StudentInfo studentInfo = StudentInfoParser.formParser(studentInfoFO);
        try {
            StudentInfo result = studentInfoService.updateStudentInfo(studentInfo);
            return ResponseWrapper.success(StudentInfoParser.viewParser(result));
        } catch (StudentException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }
}
