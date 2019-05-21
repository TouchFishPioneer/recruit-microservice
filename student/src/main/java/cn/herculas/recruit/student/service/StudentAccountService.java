package cn.herculas.recruit.student.service;

import cn.herculas.recruit.student.data.DO.StudentAccount;

public interface StudentAccountService {
    StudentAccount findStudentAccount(String studentUuid);
    StudentAccount createStudentAccount(StudentAccount studentAccount);
    StudentAccount updateStudentAccount(StudentAccount studentAccount);
    StudentAccount confirmStudentAccount(String studentEmail, String studentPassword);
}
