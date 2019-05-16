package cn.herculas.recruit.student.exception;

import cn.herculas.recruit.student.enumeration.ExceptionStatusEnum;

public class StudentException extends RuntimeException {
    private Integer exceptionCode;
    public StudentException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
