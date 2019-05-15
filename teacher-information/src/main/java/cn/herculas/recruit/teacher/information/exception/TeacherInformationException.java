package cn.herculas.recruit.teacher.information.exception;

import cn.herculas.recruit.teacher.information.enumeration.ExceptionStatusEnum;

public class TeacherInformationException extends RuntimeException {
    private Integer exceptionCode;
    public TeacherInformationException(ExceptionStatusEnum exceptionStatusEnum) {
        super(exceptionStatusEnum.getDescription());
        this.exceptionCode = exceptionStatusEnum.getCode();
    }
}
