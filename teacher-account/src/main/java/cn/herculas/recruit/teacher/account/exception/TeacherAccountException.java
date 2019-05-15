package cn.herculas.recruit.teacher.account.exception;

import cn.herculas.recruit.teacher.account.enumeration.ExceptionStatusEnum;

public class TeacherAccountException extends RuntimeException {
    private Integer exceptionCode;
    public TeacherAccountException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
