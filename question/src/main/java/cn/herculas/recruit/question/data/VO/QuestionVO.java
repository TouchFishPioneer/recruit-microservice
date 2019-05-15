package cn.herculas.recruit.question.data.VO;

import lombok.Data;

@Data
public class QuestionVO {
    private String region;
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
