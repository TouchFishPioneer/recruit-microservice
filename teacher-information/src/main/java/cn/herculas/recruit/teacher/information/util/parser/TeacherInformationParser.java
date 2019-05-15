package cn.herculas.recruit.teacher.information.util.parser;

import cn.herculas.recruit.teacher.information.data.DO.TeacherInformation;
import cn.herculas.recruit.teacher.information.data.FO.TeacherInformationFO;
import cn.herculas.recruit.teacher.information.data.VO.TeacherInformationVO;

public class TeacherInformationParser {
    public static TeacherInformation formParser(TeacherInformationFO teacherInformationFO) {
        TeacherInformation teacherInformation = new TeacherInformation();

        teacherInformation.setTeacherName(teacherInformationFO.getName());
        teacherInformation.setTeacherCardNumber(teacherInformationFO.getCard_number());
        teacherInformation.setTeacherGender(teacherInformationFO.getGender());
        teacherInformation.setTeacherTel(teacherInformationFO.getTel());
        teacherInformation.setTeacherDepartment(teacherInformationFO.getDepartment());
        teacherInformation.setTeacherDuty(teacherInformationFO.getDuty());
        teacherInformation.setTeacherGraduatedSchool(teacherInformationFO.getGraduated_school());
        teacherInformation.setTeacherRemarks(teacherInformationFO.getRemarks());
        teacherInformation.setTeacherUuid(teacherInformationFO.getUuid());

        return teacherInformation;
    }

    public static TeacherInformationVO viewParser(TeacherInformation teacherInformation) {
        TeacherInformationVO teacherInformationVO = new TeacherInformationVO();

        teacherInformationVO.setName(teacherInformation.getTeacherName());
        teacherInformationVO.setCard_number(teacherInformation.getTeacherCardNumber());
        teacherInformationVO.setGender(teacherInformation.getTeacherGender());
        teacherInformationVO.setTel(teacherInformation.getTeacherTel());
        teacherInformationVO.setDepartment(teacherInformation.getTeacherDepartment());
        teacherInformationVO.setDuty(teacherInformation.getTeacherDuty());
        teacherInformationVO.setGraduated_school(teacherInformation.getTeacherGraduatedSchool());
        teacherInformationVO.setRemarks(teacherInformation.getTeacherRemarks());
        teacherInformationVO.setUuid(teacherInformation.getTeacherUuid());

        return teacherInformationVO;
    }
}
