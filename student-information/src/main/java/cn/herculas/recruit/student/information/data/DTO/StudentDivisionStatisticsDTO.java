package cn.herculas.recruit.student.information.data.DTO;

import lombok.Data;

@Data
public class StudentDivisionStatisticsDTO {
    private Integer division;
    private Long count;

    public StudentDivisionStatisticsDTO(Integer division, Long count) {
        this.division = division;
        this.count = count;
    }
}
