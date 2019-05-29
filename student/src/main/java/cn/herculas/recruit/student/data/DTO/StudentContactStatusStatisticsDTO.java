package cn.herculas.recruit.student.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentContactStatusStatisticsDTO {
    private Integer contactStatus;
    private Long count;
}
