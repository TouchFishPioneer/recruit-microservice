package cn.herculas.recruit.student.information.data.DTO;

import lombok.Data;

@Data
public class StudentContactStatisticsDTO {
    private Integer contactStatus;
    private Long count;

    public StudentContactStatisticsDTO(Integer contactStatus, Long count) {
        this.contactStatus = contactStatus;
        this.count = count;
    }
}
