package cn.herculas.recruit.teacher.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TeacherInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherId;
    private String teacherName;
    private String teacherCardNumber;
    private Integer teacherGender;
    private String teacherTel;
    private String teacherDepartment;
    private String teacherDuty;
    private String teacherGraduatedSchool;
    private String teacherRemarks;
    private String teacherUuid;
}
