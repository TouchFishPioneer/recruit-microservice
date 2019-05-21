package cn.herculas.recruit.teacher.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class TeacherAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer teacherAccountId;
    private String teacherUsername;
    private String teacherPassword;
    private String teacherNickname;
    private Integer teacherRole;
    private Integer teacherStatus;
    private String teacherRegion;
    private String teacherAvatar;
    private String teacherUuid;
}
