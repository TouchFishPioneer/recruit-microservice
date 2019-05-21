package cn.herculas.recruit.teacher.controller;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import cn.herculas.recruit.teacher.data.FO.TeacherInfoFO;
import cn.herculas.recruit.teacher.data.VO.ResponseVO;
import cn.herculas.recruit.teacher.data.VO.TeacherInfoVO;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.service.TeacherInfoService;
import cn.herculas.recruit.teacher.util.parser.TeacherInfoParser;
import cn.herculas.recruit.teacher.util.wrapper.ResponseWrapper;
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
public class TeacherInfoController {

    private final TeacherInfoService teacherInfoService;

    public TeacherInfoController(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    @GetMapping("/list")
    public ResponseVO listTeacherInfo(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                      @RequestParam(value = "size", defaultValue = "20") Integer size) {

        // TODO: Permission check

        Page<TeacherInfo> teacherInfoPage = teacherInfoService.listTeacherInfo(PageRequest.of(page, size));
        List<TeacherInfoVO> teacherInfoVOList = teacherInfoPage.stream().map(TeacherInfoParser::viewParser).collect(Collectors.toList());
        return ResponseWrapper.success(teacherInfoVOList);
    }

    @GetMapping("/index/{uuid}")
    public ResponseVO findTeacherInfo(@PathVariable(value = "uuid") String teacherUuid) {

        // TODO: Permission check

        try {
            TeacherInfo result = teacherInfoService.findTeacherInfo(teacherUuid);
            return ResponseWrapper.success(TeacherInfoParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.NOT_FOUND, e);
        }
    }

    @PostMapping("/index")
    public ResponseVO createTeacherInfo(@Valid TeacherInfoFO teacherInfoFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        TeacherInfo teacherInfo = TeacherInfoParser.formParser(teacherInfoFO);
        try {
            TeacherInfo result = teacherInfoService.createTeacherInfo(teacherInfo);
            return ResponseWrapper.success(TeacherInfoParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/index")
    public ResponseVO updateTeacherInfo(@Valid TeacherInfoFO teacherInfoFO, BindingResult bindingResult) {

        // TODO: Permission check

        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        TeacherInfo teacherInfo = TeacherInfoParser.formParser(teacherInfoFO);
        try {
            TeacherInfo result = teacherInfoService.updateTeacherInfo(teacherInfo);
            return ResponseWrapper.success(TeacherInfoParser.viewParser(result));
        } catch (TeacherException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }
}
