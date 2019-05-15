package cn.herculas.recruit.question.service;

import cn.herculas.recruit.question.data.DO.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QuestionService {
    Page<Question> listQuestions(Pageable pageable);
    Question createQuestion(Question question);
    Question updateQuestion(Question question);
    Question voteForQuestion(String questionUuid, String studentUuid);
}
