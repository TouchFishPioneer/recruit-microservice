package cn.herculas.recruit.student.account.util.parser;

import cn.herculas.recruit.student.account.data.DO.StudentAccount;
import cn.herculas.recruit.student.account.data.FO.StudentAccountFO;
import cn.herculas.recruit.student.account.data.VO.StudentAccountVO;

public class StudentAccountParser {
    public static StudentAccount formParser(StudentAccountFO studentAccountFO) {
        StudentAccount studentAccount = new StudentAccount();

        studentAccount.setStudentEmail(studentAccountFO.getEmail());
        studentAccount.setStudentPassword(studentAccountFO.getPassword());
        studentAccount.setStudentNickname(studentAccountFO.getNickname());
        studentAccount.setStudentStatus(studentAccountFO.getStatus());
        studentAccount.setStudentUuid(studentAccountFO.getUuid());

        return studentAccount;
    }

    public static StudentAccountVO viewParser(StudentAccount studentAccount) {
        StudentAccountVO studentAccountVO = new StudentAccountVO();

        studentAccountVO.setEmail(studentAccount.getStudentEmail());
        studentAccountVO.setNickname(studentAccount.getStudentNickname());
        studentAccountVO.setStatus(studentAccount.getStudentStatus());
        studentAccountVO.setUuid(studentAccount.getStudentUuid());

        return studentAccountVO;
    }
}
