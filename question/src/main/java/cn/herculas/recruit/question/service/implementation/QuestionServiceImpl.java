package cn.herculas.recruit.question.service.implementation;

import cn.herculas.recruit.question.data.DO.Question;
import cn.herculas.recruit.question.data.DO.QuestionVote;
import cn.herculas.recruit.question.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.question.exception.QuestionException;
import cn.herculas.recruit.question.repository.QuestionRepository;
import cn.herculas.recruit.question.repository.QuestionVoteRepository;
import cn.herculas.recruit.question.service.QuestionService;
import cn.herculas.recruit.question.util.generator.KeyGenerator;
import cn.herculas.recruit.question.util.replicator.PropertyReplicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionVoteRepository questionVoteRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionVoteRepository questionVoteRepository) {
        this.questionRepository = questionRepository;
        this.questionVoteRepository = questionVoteRepository;
    }

    @Override
    public Page<Question> listQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    @Override
    public Question createQuestion(Question question) {
        if (question.getQuestionUuid() == null) {
            question.setQuestionUuid(KeyGenerator.uuidGenerator());
        }
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Question question) throws QuestionException {
        Question oldQuestion = questionRepository.findByQuestionUuid(question.getQuestionUuid());
        if (oldQuestion == null) {
            throw new QuestionException(ExceptionStatusEnum.QUESTION_NOT_EXIST);
        }
        PropertyReplicator.copyPropertiesIgnoreNull(question, oldQuestion);
        return questionRepository.save(oldQuestion);
    }

    @Override
    public Question voteForQuestion(String questionUuid, String studentUuid) throws QuestionException {
        Question question = questionRepository.findByQuestionUuid(questionUuid);
        if (question == null) {
            throw new QuestionException(ExceptionStatusEnum.QUESTION_NOT_EXIST);
        }
        QuestionVote questionVote = questionVoteRepository.findByVoteQuestionUuidAndVoteStudentUuid(questionUuid, studentUuid);
        if (questionVote == null) {
            QuestionVote newVote = new QuestionVote();
            newVote.setVoteQuestionUuid(questionUuid);
            newVote.setVoteStudentUuid(studentUuid);
            questionVoteRepository.save(newVote);
            question.setQuestionVote(question.getQuestionVote() + 1);
            return questionRepository.save(question);
        } else {
            questionVoteRepository.delete(questionVote);
            Integer vote = question.getQuestionVote();
            question.setQuestionVote((vote - 1) < 0 ? 0 : (vote - 1));
            return questionRepository.save(question);
        }
    }
}
