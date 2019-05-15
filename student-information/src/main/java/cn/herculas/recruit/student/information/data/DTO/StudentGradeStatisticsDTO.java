package cn.herculas.recruit.student.information.data.DTO;

import lombok.Data;

@Data
public class StudentGradeStatisticsDTO {
    private Integer grade;
    private Long count;

    public StudentGradeStatisticsDTO(Integer grade, Long count) {
        this.grade = grade;
        this.count = count;
    }
}
