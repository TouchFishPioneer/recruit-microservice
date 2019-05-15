package cn.herculas.recruit.question.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    private String questionRegion;
    private String questionAskerUuid;
    private String questionTheme;
    private Integer questionCategory;
    private String questionTag;
    private Integer questionStatus;
    private String questionContent;
    private Integer questionVote;
    private String questionAnswer;
    private String questionAnswererUuid;
    private String questionUuid;
}
