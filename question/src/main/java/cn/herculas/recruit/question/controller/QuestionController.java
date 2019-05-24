package cn.herculas.recruit.question.controller;

import cn.herculas.recruit.question.data.DO.Question;
import cn.herculas.recruit.question.data.FO.QuestionFO;
import cn.herculas.recruit.question.data.VO.ResponseVO;
import cn.herculas.recruit.question.enumeration.QuestionStatusEnum;
import cn.herculas.recruit.question.exception.QuestionException;
import cn.herculas.recruit.question.service.QuestionService;
import cn.herculas.recruit.question.util.parser.QuestionParser;
import cn.herculas.recruit.question.util.wrapper.ResponseWrapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/index")
    public ResponseVO listQuestions(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                    @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Page<Question> questionPage = questionService.listQuestions(PageRequest.of(page, size));
        List<QuestionFO> questionFOList = questionPage.stream().map(QuestionParser::viewParser).collect(Collectors.toList());
        return ResponseWrapper.success(questionFOList);
    }

    @PostMapping("/index")
    public ResponseVO createQuestion(@Valid QuestionFO questionFO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, bindingResult);
        }
        Question question = QuestionParser.formParser(questionFO);
        Question result = questionService.createQuestion(question);
        return ResponseWrapper.success(QuestionParser.viewParser(result));
    }

    @PatchMapping("/review/{uuid}")
    public ResponseVO reviewQuestion(@PathVariable("uuid") String questionUuid,
                                     @RequestParam("status") Integer questionStatus) {
        if (!QuestionStatusEnum.contains(questionStatus)) {
            return ResponseWrapper.error(HttpStatus.BAD_REQUEST, "status", "Do not contains this type of status.");
        }
        Question question = new Question();
        question.setQuestionUuid(questionUuid);
        question.setQuestionStatus(questionStatus);
        try {
            return this.updateQuestion(question);
        } catch (QuestionException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }

    }

    @PostMapping("/vote/{uuid}")
    public ResponseVO voteForQuestion(@PathVariable("uuid") String questionUuid,
                                      @RequestParam("student_uuid") String studentUuid) {
        try {
            Question result = questionService.voteForQuestion(questionUuid, studentUuid);
            return ResponseWrapper.success(QuestionParser.viewParser(result));
        } catch (QuestionException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }
    }

    @PatchMapping("/answer/{uuid}")
    public ResponseVO answerQuestion(@PathVariable("uuid") String questionUuid,
                                     @RequestParam("answer") String questionAnswer,
                                     @RequestParam("answerer_uuid") String answererUuid) {
        Question question = new Question();
        question.setQuestionUuid(questionUuid);
        question.setQuestionAnswer(questionAnswer);
        question.setQuestionAnswererUuid(answererUuid);
        try {
            return this.updateQuestion(question);
        } catch (QuestionException e) {
            return ResponseWrapper.error(HttpStatus.FORBIDDEN, e);
        }

    }

    private ResponseVO updateQuestion(Question question) throws QuestionException {
        Question result = questionService.updateQuestion(question);
        return ResponseWrapper.success(QuestionParser.viewParser(result));
    }
}
