package cn.herculas.recruit.teacher.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherDepartmentStatisticsDTO {
    private String department;
    private Long count;
}
