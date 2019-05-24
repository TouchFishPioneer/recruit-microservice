package cn.herculas.recruit.question.data.FO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class QuestionFO {
    private String region;
    @NotEmpty(message = "The uuid of asker should not be empty.")
    private String asker_uuid;
    private String theme;
    private Integer category;
    private String tag;
    private Integer status;
    private String content;
    private Integer vote;
    private String answer;
    private String answerer_uuid;
    private String uuid;
}
