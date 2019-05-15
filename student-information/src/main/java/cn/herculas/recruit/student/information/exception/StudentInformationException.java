package cn.herculas.recruit.student.information.exception;

import cn.herculas.recruit.student.information.enumeration.ExceptionStatusEnum;

public class StudentInformationException extends RuntimeException {
    private Integer exceptionCode;
    public StudentInformationException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
