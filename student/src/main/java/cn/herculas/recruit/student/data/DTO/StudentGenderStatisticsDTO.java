package cn.herculas.recruit.student.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentGenderStatisticsDTO {
    private Integer gender;
    private Long count;
}
