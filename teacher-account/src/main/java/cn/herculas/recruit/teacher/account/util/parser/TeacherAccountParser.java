package cn.herculas.recruit.teacher.account.util.parser;

import cn.herculas.recruit.teacher.account.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.account.data.FO.TeacherAccountFO;
import cn.herculas.recruit.teacher.account.data.VO.TeacherAccountVO;

public class TeacherAccountParser {
    public static TeacherAccount formParser(TeacherAccountFO teacherAccountFO) {
        TeacherAccount teacherAccount = new TeacherAccount();

        teacherAccount.setTeacherUsername(teacherAccountFO.getUsername());
        teacherAccount.setTeacherPassword(teacherAccountFO.getPassword());
        teacherAccount.setTeacherNickname(teacherAccountFO.getNickname());
        teacherAccount.setTeacherRole(teacherAccountFO.getRole());
        teacherAccount.setTeacherStatus(teacherAccountFO.getStatus());
        teacherAccount.setTeacherRegion(teacherAccountFO.getRegion());
        teacherAccount.setTeacherAvatar(teacherAccountFO.getAvatar());
        teacherAccount.setTeacherUuid(teacherAccountFO.getUuid());

        return teacherAccount;
    }

    public static TeacherAccountVO viewParser(TeacherAccount teacherAccount) {
        TeacherAccountVO teacherAccountVO = new TeacherAccountVO();

        teacherAccountVO.setUsername(teacherAccount.getTeacherUsername());
        teacherAccountVO.setNickname(teacherAccount.getTeacherNickname());
        teacherAccountVO.setRole(teacherAccount.getTeacherRole());
        teacherAccountVO.setStatus(teacherAccount.getTeacherStatus());
        teacherAccountVO.setRegion(teacherAccount.getTeacherRegion());
        teacherAccountVO.setAvatar(teacherAccount.getTeacherAvatar());
        teacherAccountVO.setUuid(teacherAccount.getTeacherUuid());

        return teacherAccountVO;
    }
}
