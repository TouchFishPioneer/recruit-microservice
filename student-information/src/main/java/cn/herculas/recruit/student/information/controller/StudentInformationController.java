package cn.herculas.recruit.student.information.controller;

import cn.herculas.recruit.student.information.data.DO.StudentInformation;
import cn.herculas.recruit.student.information.data.FO.StudentInformationFO;
import cn.herculas.recruit.student.information.data.VO.ResultVO;
import cn.herculas.recruit.student.information.data.VO.StudentInformationVO;
import cn.herculas.recruit.student.information.exception.StudentInformationException;
import cn.herculas.recruit.student.information.service.StudentInformationService;
import cn.herculas.recruit.student.information.util.parser.StudentInformationParser;
import cn.herculas.recruit.student.information.util.wrapper.ResultWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/info")
public class StudentInformationController {
    private final StudentInformationService studentInformationService;

    public StudentInformationController(StudentInformationService studentInformationService) {
        this.studentInformationService = studentInformationService;
    }

    @GetMapping("/index")
    public ResultVO listStudent(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Page<StudentInformation> studentInformationPage = studentInformationService.listStudentInformation(PageRequest.of(page, size));
        List<StudentInformationVO> studentInformationVOList = new ArrayList<>();
        for (StudentInformation studentInformation : studentInformationPage) {
            studentInformationVOList.add(StudentInformationParser.viewParser(studentInformation));
        }
        return ResultWrapper.success(studentInformationVOList);
    }

    @GetMapping("/index/{uuid}")
    public ResultVO findStudent(@PathVariable(value = "uuid") String studentUuid) {
        try {
            StudentInformation result = studentInformationService.findStudentInformation(studentUuid);
            return ResultWrapper.success(StudentInformationParser.viewParser(result));
        } catch (StudentInformationException e) {
            return ResultWrapper.error(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/index")
    public ResultVO createStudent(@Valid StudentInformationFO studentInformationFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        StudentInformation studentInformation = StudentInformationParser.formParser(studentInformationFO);

        try {
            StudentInformation result = studentInformationService.createStudentInformation(studentInformation);
            return ResultWrapper.success(StudentInformationParser.viewParser(result));
        } catch (StudentInformationException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/index")
    public ResultVO updateStudent(@Valid StudentInformationFO studentInformationFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        StudentInformation studentInformation = StudentInformationParser.formParser(studentInformationFO);

        try {
            StudentInformation result = studentInformationService.updateStudentInformation(studentInformation);
            return ResultWrapper.success(StudentInformationParser.viewParser(result));
        } catch (StudentInformationException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
