package cn.herculas.recruit.question.util.parser;

import cn.herculas.recruit.question.data.DO.Question;
import cn.herculas.recruit.question.data.FO.QuestionFO;
import cn.herculas.recruit.question.enumeration.QuestionStatusEnum;

public class QuestionParser {
    public static Question formParser(QuestionFO questionFO) {
        Question question = new Question();

        question.setQuestionRegion(questionFO.getRegion());
        question.setQuestionAskerUuid(questionFO.getAsker_uuid());
        question.setQuestionTheme(questionFO.getTheme());
        question.setQuestionCategory(questionFO.getCategory());
        question.setQuestionTag(questionFO.getTag());

        if (questionFO.getStatus() == null) {
            question.setQuestionStatus(QuestionStatusEnum.NOT_REVIEWED.getCode());
        } else {
            question.setQuestionStatus(questionFO.getStatus());
        }

        question.setQuestionContent(questionFO.getContent());

        if (questionFO.getVote() == null) {
            question.setQuestionVote(0);
        } else {
            question.setQuestionVote(questionFO.getVote());
        }

        question.setQuestionAnswer(questionFO.getAnswer());
        question.setQuestionAnswererUuid(questionFO.getAnswerer_uuid());
        question.setQuestionUuid(questionFO.getUuid());

        return question;
    }

    public static QuestionFO viewParser(Question question) {
        QuestionFO questionFO = new QuestionFO();

        questionFO.setRegion(question.getQuestionRegion());
        questionFO.setAsker_uuid(question.getQuestionAskerUuid());
        questionFO.setTheme(question.getQuestionTheme());
        questionFO.setCategory(question.getQuestionCategory());
        questionFO.setTag(question.getQuestionTag());
        questionFO.setStatus(question.getQuestionStatus());
        questionFO.setContent(question.getQuestionContent());
        questionFO.setVote(question.getQuestionVote());
        questionFO.setAnswer(question.getQuestionAnswer());
        questionFO.setAnswerer_uuid(question.getQuestionAnswererUuid());
        questionFO.setUuid(question.getQuestionUuid());

        return questionFO;
    }
}
