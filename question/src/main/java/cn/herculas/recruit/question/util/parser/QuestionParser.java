package cn.herculas.recruit.question.util.parser;

import cn.herculas.recruit.question.data.DO.Question;
import cn.herculas.recruit.question.data.VO.QuestionVO;
import cn.herculas.recruit.question.enumeration.QuestionStatusEnum;

public class QuestionParser {
    public static Question formParser(QuestionVO questionVO) {
        Question question = new Question();

        question.setQuestionRegion(questionVO.getRegion());
        question.setQuestionAskerUuid(questionVO.getAsker_uuid());
        question.setQuestionTheme(questionVO.getTheme());
        question.setQuestionCategory(questionVO.getCategory());
        question.setQuestionTag(questionVO.getTag());

        if (questionVO.getStatus() == null) {
            question.setQuestionStatus(QuestionStatusEnum.NOT_REVIEWED.getCode());
        } else {
            question.setQuestionStatus(questionVO.getStatus());
        }

        question.setQuestionContent(questionVO.getContent());

        if (questionVO.getVote() == null) {
            question.setQuestionVote(0);
        } else {
            question.setQuestionVote(questionVO.getVote());
        }

        question.setQuestionAnswer(questionVO.getAnswer());
        question.setQuestionAnswererUuid(questionVO.getAnswerer_uuid());
        question.setQuestionUuid(questionVO.getUuid());

        return question;
    }

    public static QuestionVO viewParser(Question question) {
        QuestionVO questionVO = new QuestionVO();

        questionVO.setRegion(question.getQuestionRegion());
        questionVO.setAsker_uuid(question.getQuestionAskerUuid());
        questionVO.setTheme(question.getQuestionTheme());
        questionVO.setCategory(question.getQuestionCategory());
        questionVO.setTag(question.getQuestionTag());
        questionVO.setStatus(question.getQuestionStatus());
        questionVO.setContent(question.getQuestionContent());
        questionVO.setVote(question.getQuestionVote());
        questionVO.setAnswer(question.getQuestionAnswer());
        questionVO.setAnswerer_uuid(question.getQuestionAnswererUuid());
        questionVO.setUuid(question.getQuestionUuid());

        return questionVO;
    }
}
