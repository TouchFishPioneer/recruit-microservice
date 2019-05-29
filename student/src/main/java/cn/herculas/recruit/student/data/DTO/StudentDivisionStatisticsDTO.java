package cn.herculas.recruit.student.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDivisionStatisticsDTO {
    private Integer division;
    private Long count;
}
