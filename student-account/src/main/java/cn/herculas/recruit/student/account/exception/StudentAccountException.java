package cn.herculas.recruit.student.account.exception;

import cn.herculas.recruit.student.account.enumeration.ExceptionStatusEnum;

public class StudentAccountException extends RuntimeException {
    private Integer exceptionCode;
    public StudentAccountException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
