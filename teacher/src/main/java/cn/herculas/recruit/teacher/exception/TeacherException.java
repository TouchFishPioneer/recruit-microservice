package cn.herculas.recruit.teacher.exception;

import cn.herculas.recruit.teacher.enumeration.ExceptionStatusEnum;
import lombok.Getter;

@Getter
public class TeacherException extends RuntimeException {
    private Integer exceptionCode;
    public TeacherException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
