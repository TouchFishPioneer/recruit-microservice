package cn.herculas.recruit.teacher.data.VO;

import lombok.Data;

@Data
public class TeacherInfoVO {
    private String name;
    private String card_number;
    private Integer gender;
    private String tel;
    private String department;
    private String duty;
    private String graduated_school;
    private String remarks;
    private String uuid;
}
