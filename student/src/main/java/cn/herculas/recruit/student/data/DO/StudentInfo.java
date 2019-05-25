package cn.herculas.recruit.student.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity(name = "student_information")
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentId;
    private String studentName;
    private Integer studentGender;
    private String studentTel;
    private Date studentBirthday;
    private String studentIdentityNumber;
    private String studentAdmissionNumber;
    private String studentRegion;
    private String studentSchool;
    private Integer studentMark;
    private Integer studentDivision;
    private Integer studentRank;
    private String studentRemarks;
    private Integer studentInfoSource;
    private Integer studentContactStatus;
    private String studentIntentionalMajor;
    private String studentUnintentionalMajor;
    private Integer studentGrade;
    private String studentUuid;
}
