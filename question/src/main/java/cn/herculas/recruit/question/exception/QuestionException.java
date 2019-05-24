package cn.herculas.recruit.question.exception;

import cn.herculas.recruit.question.enumeration.ExceptionStatusEnum;
import lombok.Getter;

@Getter
public class QuestionException extends RuntimeException {

    private Integer exceptionCode;

    public QuestionException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
