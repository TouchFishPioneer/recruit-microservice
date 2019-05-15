package cn.herculas.recruit.student.information.data.VO;

import lombok.Data;

import java.util.Date;

@Data
public class StudentInformationVO {
    private String name;
    private Integer gender;
    private String tel;
    private Date birthday;
    private String identity_number;
    private String admission_number;
    private String region;
    private String school;
    private Integer mark;
    private Integer division;
    private Integer rank;
    private String remarks;
    private Integer info_source;
    private Integer contact_status;
    private String intentional_major;
    private String unintentional_major;
    private Integer grade;
    private String uuid;
}
