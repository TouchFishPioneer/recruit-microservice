package cn.herculas.recruit.student.information.service.implementation;

import cn.herculas.recruit.student.information.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.student.information.enumeration.StudentInfoSourceEnum;
import cn.herculas.recruit.student.information.exception.StudentInformationException;
import cn.herculas.recruit.student.information.repository.StudentInformationRepository;
import cn.herculas.recruit.student.information.service.StudentInformationService;
import cn.herculas.recruit.student.information.util.generator.KeyGenerator;
import cn.herculas.recruit.student.information.data.DO.StudentInformation;
import cn.herculas.recruit.student.information.util.replicator.PropertyReplicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentInformationServiceImpl implements StudentInformationService {

    private final StudentInformationRepository studentInformationRepository;

    public StudentInformationServiceImpl(StudentInformationRepository studentInformationRepository) {
        this.studentInformationRepository = studentInformationRepository;
    }

    @Override
    public StudentInformation createStudentInformation(StudentInformation studentInformation) {
        if (studentInformation.getStudentUuid() == null)
            studentInformation.setStudentUuid(KeyGenerator.uuidGenerator());

        // Data source configuration
        // 1. Completely new data
        if (studentInformationRepository.findByStudentIdentityNumber(studentInformation.getStudentIdentityNumber()) == null &&
                studentInformationRepository.findByStudentAdmissionNumber(studentInformation.getStudentAdmissionNumber()) == null) {
            return studentInformationRepository.save(studentInformation);
        } else {
            StudentInformation oldStudentInformation = studentInformationRepository.findByStudentIdentityNumber(studentInformation.getStudentIdentityNumber());
            if (oldStudentInformation == null)
                oldStudentInformation = studentInformationRepository.findByStudentAdmissionNumber(studentInformation.getStudentAdmissionNumber());

            // 2. Teacher imported, and then student initialized
            if (oldStudentInformation.getStudentInfoSource().equals(StudentInfoSourceEnum.IMPORT.getCode())) {
                return this.getStudentInformation(studentInformation, oldStudentInformation);
            }

            // 3. Student initialized, and then teacher imported
            if (oldStudentInformation.getStudentInfoSource().equals(StudentInfoSourceEnum.REGISTER.getCode())) {
                return this.getStudentInformation(oldStudentInformation, studentInformation);
            }

            // 4. Imported and initialized
            if (oldStudentInformation.getStudentInfoSource().equals(StudentInfoSourceEnum.IMPORT_AND_REGISTER.getCode())) {
                throw new StudentInformationException(ExceptionStatusEnum.STUDENT_ALREADY_EXIST);
            }
        }
        throw new StudentInformationException(ExceptionStatusEnum.STUDENT_ALREADY_EXIST);
    }

    private StudentInformation getStudentInformation(StudentInformation untrustedSource, StudentInformation trustedSource) {
        Integer studentMark = trustedSource.getStudentMark();
        Integer studentRank = trustedSource.getStudentRank();
        PropertyReplicator.copyPropertiesIgnoreNull(untrustedSource, trustedSource);
        trustedSource.setStudentMark(studentMark);
        trustedSource.setStudentRank(studentRank);
        trustedSource.setStudentInfoSource(StudentInfoSourceEnum.IMPORT_AND_REGISTER.getCode());
        return studentInformationRepository.save(trustedSource);
    }

    @Override
    public StudentInformation updateStudentInformation(StudentInformation studentInformation) {
        StudentInformation oldStudentInformation = studentInformationRepository.findByStudentUuid(studentInformation.getStudentUuid());
        if (oldStudentInformation == null)
            throw new StudentInformationException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        PropertyReplicator.copyPropertiesIgnoreNull(studentInformation, oldStudentInformation);
        return studentInformationRepository.save(oldStudentInformation);
    }

    @Override
    public Page<StudentInformation> listStudentInformation(Pageable pageable) {
        return studentInformationRepository.findAll(pageable);
    }

    @Override
    public StudentInformation findStudentInformation(String studentUuid) {
        StudentInformation studentInformation = studentInformationRepository.findByStudentUuid(studentUuid);
        if (studentInformation == null)
            throw new StudentInformationException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        return studentInformation;
    }
}
