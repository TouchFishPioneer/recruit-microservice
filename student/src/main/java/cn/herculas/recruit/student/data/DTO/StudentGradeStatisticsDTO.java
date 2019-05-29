package cn.herculas.recruit.student.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGradeStatisticsDTO {
    private Integer grade;
    private Long count;
}
