package cn.herculas.recruit.student.service;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentInfoService {
    Page<StudentInfo> listStudentInfo(String regionFormat, Pageable pageable);
    StudentInfo findStudentInfo(String studentUuid);
    StudentInfo createStudentInfo(StudentInfo studentInfo);
    StudentInfo updateStudentInfo(StudentInfo studentInfo);
}
