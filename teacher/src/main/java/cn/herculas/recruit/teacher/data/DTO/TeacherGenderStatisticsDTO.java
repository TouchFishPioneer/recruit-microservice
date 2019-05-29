package cn.herculas.recruit.teacher.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherGenderStatisticsDTO {
    private Integer gender;
    private Long count;
}
