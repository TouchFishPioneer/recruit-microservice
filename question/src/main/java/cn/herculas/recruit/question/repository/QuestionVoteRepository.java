package cn.herculas.recruit.question.repository;

import cn.herculas.recruit.question.data.DO.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote, Integer> {
    QuestionVote findByVoteQuestionUuidAndVoteStudentUuid(String voteQuestionUuid, String voteStudentUuid);
}
