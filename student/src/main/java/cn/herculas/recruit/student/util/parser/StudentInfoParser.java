package cn.herculas.recruit.student.util.parser;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import cn.herculas.recruit.student.data.FO.StudentInfoFO;
import cn.herculas.recruit.student.data.VO.StudentInfoVO;
import cn.herculas.recruit.student.enumeration.StudentContactStatusEnum;
import cn.herculas.recruit.student.enumeration.StudentInfoSourceEnum;

public class StudentInfoParser {
    public static StudentInfo formParser(StudentInfoFO studentInfoFO) {
        StudentInfo studentInformation = new StudentInfo();

        studentInformation.setStudentName(studentInfoFO.getName());
        studentInformation.setStudentGender(studentInfoFO.getGender());
        studentInformation.setStudentTel(studentInfoFO.getTel());
        studentInformation.setStudentBirthday(studentInfoFO.getBirthday());
        studentInformation.setStudentIdentityNumber(studentInfoFO.getIdentity_number());
        studentInformation.setStudentAdmissionNumber(studentInfoFO.getAdmission_number());
        studentInformation.setStudentRegion(studentInfoFO.getRegion());
        studentInformation.setStudentSchool(studentInfoFO.getSchool());
        studentInformation.setStudentMark(studentInfoFO.getMark());
        studentInformation.setStudentDivision(studentInfoFO.getDivision());
        studentInformation.setStudentRank(studentInfoFO.getRank());
        studentInformation.setStudentRemarks(studentInfoFO.getRemarks());


        if (studentInfoFO.getInfo_source() != null) {
            studentInformation.setStudentInfoSource(studentInfoFO.getInfo_source());
        } else {
            if (studentInfoFO.getUuid() != null) {
                studentInformation.setStudentInfoSource(StudentInfoSourceEnum.REGISTER.getCode());
            } else {
                studentInformation.setStudentInfoSource(StudentInfoSourceEnum.IMPORT.getCode());
            }
        }

        if (studentInfoFO.getContact_status() != null) {
            studentInformation.setStudentContactStatus(studentInfoFO.getContact_status());
        } else {
            studentInformation.setStudentContactStatus(StudentContactStatusEnum.NOT_CONTACTED.getCode());
        }

        studentInformation.setStudentIntentionalMajor(studentInfoFO.getIntentional_major());
        studentInformation.setStudentUnintentionalMajor(studentInfoFO.getUnintentional_major());
        studentInformation.setStudentGrade(studentInfoFO.getGrade());
        studentInformation.setStudentUuid(studentInfoFO.getUuid());

        return studentInformation;
    }

    public static StudentInfoVO viewParser(StudentInfo studentInfo) {
        StudentInfoVO studentInformationVO = new StudentInfoVO();

        studentInformationVO.setName(studentInfo.getStudentName());
        studentInformationVO.setGender(studentInfo.getStudentGender());
        studentInformationVO.setTel(studentInfo.getStudentTel());
        studentInformationVO.setBirthday(studentInfo.getStudentBirthday());
        studentInformationVO.setIdentity_number(studentInfo.getStudentIdentityNumber());
        studentInformationVO.setAdmission_number(studentInfo.getStudentAdmissionNumber());
        studentInformationVO.setRegion(studentInfo.getStudentRegion());
        studentInformationVO.setSchool(studentInfo.getStudentSchool());
        studentInformationVO.setMark(studentInfo.getStudentMark());
        studentInformationVO.setDivision(studentInfo.getStudentDivision());
        studentInformationVO.setRank(studentInfo.getStudentRank());
        studentInformationVO.setRemarks(studentInfo.getStudentRemarks());
        studentInformationVO.setInfo_source(studentInfo.getStudentInfoSource());
        studentInformationVO.setContact_status(studentInfo.getStudentContactStatus());
        studentInformationVO.setIntentional_major(studentInfo.getStudentIntentionalMajor());
        studentInformationVO.setUnintentional_major(studentInfo.getStudentUnintentionalMajor());
        studentInformationVO.setGrade(studentInfo.getStudentGrade());
        studentInformationVO.setUuid(studentInfo.getStudentUuid());

        return studentInformationVO;
    }
}
