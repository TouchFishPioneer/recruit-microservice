package cn.herculas.recruit.teacher.data.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherRegionStatisticsDTO {
    private String region;
    private Long count;
    public String getProvince() {
        return region.substring(0, 2);
    }
    public String getCity() {
        return region.substring(0, 4);
    }
}
