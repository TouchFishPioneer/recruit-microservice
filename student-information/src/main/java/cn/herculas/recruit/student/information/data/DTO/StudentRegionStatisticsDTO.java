package cn.herculas.recruit.student.information.data.DTO;

import lombok.Data;

@Data
public class StudentRegionStatisticsDTO {
    private String region;
    private Long count;

    public StudentRegionStatisticsDTO(String region, Long count) {
        this.region = region;
        this.count = count;
    }
}
