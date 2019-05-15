package cn.herculas.recruit.teacher.information.controller;

import cn.herculas.recruit.teacher.information.data.DO.TeacherInformation;
import cn.herculas.recruit.teacher.information.data.VO.ResultVO;
import cn.herculas.recruit.teacher.information.util.parser.TeacherInformationParser;
import cn.herculas.recruit.teacher.information.util.wrapper.ResultWrapper;
import cn.herculas.recruit.teacher.information.data.FO.TeacherInformationFO;
import cn.herculas.recruit.teacher.information.data.VO.TeacherInformationVO;
import cn.herculas.recruit.teacher.information.exception.TeacherInformationException;
import cn.herculas.recruit.teacher.information.service.TeacherInformationService;
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
public class TeacherInformationController {

    private final TeacherInformationService teacherInformationService;

    public TeacherInformationController(TeacherInformationService teacherInformationService) {
        this.teacherInformationService = teacherInformationService;
    }

    @GetMapping("/index")
    public ResultVO listTeacher(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Page<TeacherInformation> teacherInformationPage = teacherInformationService.listTeacherInformation(PageRequest.of(page, size));
        List<TeacherInformationVO> teacherInformationVOList = new ArrayList<>();
        for (TeacherInformation teacherInformation : teacherInformationPage) {
            teacherInformationVOList.add(TeacherInformationParser.viewParser(teacherInformation));
        }
        return ResultWrapper.success(teacherInformationVOList);
    }

    @GetMapping("/index/{uuid}")
    public ResultVO findTeacher(@PathVariable(value = "uuid") String teacherUuid) {
        try {
            TeacherInformation teacherInformation = teacherInformationService.findTeacherInformation(teacherUuid);
            return ResultWrapper.success(TeacherInformationParser.viewParser(teacherInformation));
        } catch (TeacherInformationException e) {
            return ResultWrapper.error(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/index")
    public ResultVO createTeacher(@Valid TeacherInformationFO teacherInformationFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        TeacherInformation teacherInformation = TeacherInformationParser.formParser(teacherInformationFO);

        try {
            TeacherInformation result = teacherInformationService.createTeacherInformation(teacherInformation);
            return ResultWrapper.success(TeacherInformationParser.viewParser(result));
        } catch (TeacherInformationException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }

    @PatchMapping("/index")
    public ResultVO updateTeacher(@Valid TeacherInformationFO teacherInformationFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultWrapper.error(HttpStatus.BAD_REQUEST, "Invalid Parameters");

        TeacherInformation teacherInformation = TeacherInformationParser.formParser(teacherInformationFO);

        try {
            TeacherInformation result = teacherInformationService.updateTeacherInformation(teacherInformation);
            return ResultWrapper.success(TeacherInformationParser.viewParser(result));
        } catch (TeacherInformationException e) {
            return ResultWrapper.error(HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
