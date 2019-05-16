package cn.herculas.recruit.student.data.DO;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class StudentAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer studentAccountId;
    private String studentEmail;
    private String studentPassword;
    private String studentNickname;
    private Integer studentStatus;
    private String studentUuid;
}
