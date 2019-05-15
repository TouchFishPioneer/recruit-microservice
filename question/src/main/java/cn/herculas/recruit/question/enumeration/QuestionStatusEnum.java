package cn.herculas.recruit.question.enumeration;

import lombok.Getter;

@Getter
public enum QuestionStatusEnum {
    NOT_REVIEWED(0, "This question has not been reviewed."),
    PASS_REVIEW(1, "This question has passed the review but not been answered."),
    REVIEW_FORBIDDEN(2, "This question has been forbidden by the reviewer."),
    ANSWERING(3, "This question is now being answered by a teacher."),
    ANSWERED(4, "This question has been answered.");

    private Integer code;
    private String description;

    QuestionStatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public static boolean contains(Integer code) {
        for (QuestionStatusEnum questionStatusEnum : QuestionStatusEnum.values()) {
            if (questionStatusEnum.code.equals(code))
                return true;
        }
        return false;
    }
}
