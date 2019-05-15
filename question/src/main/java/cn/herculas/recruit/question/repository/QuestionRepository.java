package cn.herculas.recruit.question.repository;

import cn.herculas.recruit.question.data.DO.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Question findByQuestionUuid(String questionUuid);
}
