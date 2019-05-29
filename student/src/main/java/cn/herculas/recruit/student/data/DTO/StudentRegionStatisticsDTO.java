package cn.herculas.recruit.student.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRegionStatisticsDTO {
    private String region;
    private Long count;
    public String getProvince() {
        return region.substring(0, 2);
    }
    public String getCity() {
        return region.substring(0, 4);
    }
}
