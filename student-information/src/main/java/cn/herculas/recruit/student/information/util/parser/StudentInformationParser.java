package cn.herculas.recruit.student.information.util.parser;

import cn.herculas.recruit.student.information.data.FO.StudentInformationFO;
import cn.herculas.recruit.student.information.data.VO.StudentInformationVO;
import cn.herculas.recruit.student.information.enumeration.StudentContactStatusEnum;
import cn.herculas.recruit.student.information.enumeration.StudentInfoSourceEnum;
import cn.herculas.recruit.student.information.data.DO.StudentInformation;

public class StudentInformationParser {
    public static StudentInformation formParser(StudentInformationFO studentInformationFO) {
        StudentInformation studentInformation = new StudentInformation();

        studentInformation.setStudentName(studentInformationFO.getName());
        studentInformation.setStudentGender(studentInformationFO.getGender());
        studentInformation.setStudentTel(studentInformationFO.getTel());
        studentInformation.setStudentBirthday(studentInformationFO.getBirthday());
        studentInformation.setStudentIdentityNumber(studentInformationFO.getIdentity_number());
        studentInformation.setStudentAdmissionNumber(studentInformationFO.getAdmission_number());
        studentInformation.setStudentRegion(studentInformationFO.getRegion());
        studentInformation.setStudentSchool(studentInformationFO.getSchool());
        studentInformation.setStudentMark(studentInformationFO.getMark());
        studentInformation.setStudentDivision(studentInformationFO.getDivision());
        studentInformation.setStudentRank(studentInformationFO.getRank());
        studentInformation.setStudentRemarks(studentInformationFO.getRemarks());


        if (studentInformationFO.getInfo_source() != null) {
            studentInformation.setStudentInfoSource(studentInformationFO.getInfo_source());
        } else {
            if (studentInformationFO.getUuid() != null) {
                studentInformation.setStudentInfoSource(StudentInfoSourceEnum.REGISTER.getCode());
            } else {
                studentInformation.setStudentInfoSource(StudentInfoSourceEnum.IMPORT.getCode());
            }
        }

        if (studentInformationFO.getContact_status() != null) {
            studentInformation.setStudentContactStatus(studentInformationFO.getContact_status());
        } else {
            studentInformation.setStudentContactStatus(StudentContactStatusEnum.NOT_CONTACTED.getCode());
        }


        studentInformation.setStudentIntentionalMajor(studentInformationFO.getIntentional_major());
        studentInformation.setStudentUnintentionalMajor(studentInformationFO.getUnintentional_major());
        studentInformation.setStudentGrade(studentInformationFO.getGrade());
        studentInformation.setStudentUuid(studentInformationFO.getUuid());

        return studentInformation;
    }

    public static StudentInformationVO viewParser(StudentInformation studentInformation) {
        StudentInformationVO studentInformationVO = new StudentInformationVO();

        studentInformationVO.setName(studentInformation.getStudentName());
        studentInformationVO.setGender(studentInformation.getStudentGender());
        studentInformationVO.setTel(studentInformation.getStudentTel());
        studentInformationVO.setBirthday(studentInformation.getStudentBirthday());
        studentInformationVO.setIdentity_number(studentInformation.getStudentIdentityNumber());
        studentInformationVO.setAdmission_number(studentInformation.getStudentAdmissionNumber());
        studentInformationVO.setRegion(studentInformation.getStudentRegion());
        studentInformationVO.setSchool(studentInformation.getStudentSchool());
        studentInformationVO.setMark(studentInformation.getStudentMark());
        studentInformationVO.setDivision(studentInformation.getStudentDivision());
        studentInformationVO.setRank(studentInformation.getStudentRank());
        studentInformationVO.setRemarks(studentInformation.getStudentRemarks());
        studentInformationVO.setInfo_source(studentInformation.getStudentInfoSource());
        studentInformationVO.setContact_status(studentInformation.getStudentContactStatus());
        studentInformationVO.setIntentional_major(studentInformation.getStudentIntentionalMajor());
        studentInformationVO.setUnintentional_major(studentInformation.getStudentUnintentionalMajor());
        studentInformationVO.setGrade(studentInformation.getStudentGrade());
        studentInformationVO.setUuid(studentInformation.getStudentUuid());

        return studentInformationVO;
    }
}
