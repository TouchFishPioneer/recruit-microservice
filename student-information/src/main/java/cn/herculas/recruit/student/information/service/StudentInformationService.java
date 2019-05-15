package cn.herculas.recruit.student.information.service;

import cn.herculas.recruit.student.information.data.DO.StudentInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentInformationService {
    StudentInformation createStudentInformation(StudentInformation studentInformation);
    StudentInformation updateStudentInformation(StudentInformation studentInformation);
    Page<StudentInformation> listStudentInformation(Pageable pageable);
    StudentInformation findStudentInformation(String studentUuid);
}
