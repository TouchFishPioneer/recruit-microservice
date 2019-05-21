package cn.herculas.recruit.teacher.util.parser;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import cn.herculas.recruit.teacher.data.FO.TeacherInfoFO;
import cn.herculas.recruit.teacher.data.VO.TeacherInfoVO;

public class TeacherInfoParser {
    public static TeacherInfo formParser(TeacherInfoFO teacherInfoFO) {
        TeacherInfo teacherInfo = new TeacherInfo();

        teacherInfo.setTeacherName(teacherInfoFO.getName());
        teacherInfo.setTeacherCardNumber(teacherInfoFO.getCard_number());
        teacherInfo.setTeacherGender(teacherInfoFO.getGender());
        teacherInfo.setTeacherTel(teacherInfoFO.getTel());
        teacherInfo.setTeacherDepartment(teacherInfoFO.getDepartment());
        teacherInfo.setTeacherDuty(teacherInfoFO.getDuty());
        teacherInfo.setTeacherGraduatedSchool(teacherInfoFO.getGraduated_school());
        teacherInfo.setTeacherRemarks(teacherInfoFO.getRemarks());
        teacherInfo.setTeacherUuid(teacherInfoFO.getUuid());

        return teacherInfo;
    }

    public static TeacherInfoVO viewParser(TeacherInfo teacherInfo) {
        TeacherInfoVO teacherInfoVO = new TeacherInfoVO();

        teacherInfoVO.setName(teacherInfo.getTeacherName());
        teacherInfoVO.setCard_number(teacherInfo.getTeacherCardNumber());
        teacherInfoVO.setGender(teacherInfo.getTeacherGender());
        teacherInfoVO.setTel(teacherInfo.getTeacherTel());
        teacherInfoVO.setDepartment(teacherInfo.getTeacherDepartment());
        teacherInfoVO.setDuty(teacherInfo.getTeacherDuty());
        teacherInfoVO.setGraduated_school(teacherInfo.getTeacherGraduatedSchool());
        teacherInfoVO.setRemarks(teacherInfo.getTeacherRemarks());
        teacherInfoVO.setUuid(teacherInfo.getTeacherUuid());

        return teacherInfoVO;
    }
}
