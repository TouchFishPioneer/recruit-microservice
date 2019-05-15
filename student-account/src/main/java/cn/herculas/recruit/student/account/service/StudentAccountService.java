package cn.herculas.recruit.student.account.service;

import cn.herculas.recruit.student.account.data.DO.StudentAccount;

public interface StudentAccountService {
    StudentAccount createStudentAccount(StudentAccount studentAccount);
    StudentAccount updateStudentAccount(StudentAccount studentAccount);
    StudentAccount findStudentAccountByUuid(String studentUuid);
}
