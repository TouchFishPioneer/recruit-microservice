package cn.herculas.recruit.student.service.implementation;

import cn.herculas.recruit.student.data.DO.StudentInfo;
import cn.herculas.recruit.student.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.student.enumeration.StudentInfoSourceEnum;
import cn.herculas.recruit.student.exception.StudentException;
import cn.herculas.recruit.student.repository.StudentInfoRepository;
import cn.herculas.recruit.student.service.StudentInfoService;
import cn.herculas.recruit.student.util.generator.KeyGenerator;
import cn.herculas.recruit.student.util.replicator.PropertyReplicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    private final StudentInfoRepository studentInfoRepository;

    public StudentInfoServiceImpl(StudentInfoRepository studentInfoRepository) {
        this.studentInfoRepository = studentInfoRepository;
    }

    @Override
    public Page<StudentInfo> listStudentInfo(String regionFormat, Pageable pageable) {
        return studentInfoRepository.findAllByStudentRegionLike(regionFormat, pageable);
    }

    @Override
    public StudentInfo findStudentInfo(String studentUuid) throws StudentException {
        StudentInfo studentInfo = studentInfoRepository.findByStudentUuid(studentUuid);
        if (studentInfo == null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        }
        return studentInfo;
    }

    @Override
    public StudentInfo createStudentInfo(StudentInfo studentInfo) throws StudentException {
        if (studentInfoRepository.findByStudentUuid(studentInfo.getStudentUuid()) != null ||
                studentInfoRepository.findByStudentIdentityNumber(studentInfo.getStudentIdentityNumber()) != null ||
                studentInfoRepository.findByStudentAdmissionNumber(studentInfo.getStudentAdmissionNumber()) != null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_ALREADY_EXIST);
        }

        if (studentInfo.getStudentUuid() == null) {
            studentInfo.setStudentUuid(KeyGenerator.uuidGenerator());
        }
        return studentInfoRepository.save(studentInfo);
    }

    @Override
    public StudentInfo updateStudentInfo(StudentInfo studentInfo) throws StudentException {

        StudentInfo oldStudentInfo = Stream.<Supplier<StudentInfo>>of(
                () -> studentInfoRepository.findByStudentUuid(studentInfo.getStudentUuid()),
                () -> studentInfoRepository.findByStudentIdentityNumber(studentInfo.getStudentIdentityNumber()),
                () -> studentInfoRepository.findByStudentAdmissionNumber(studentInfo.getStudentAdmissionNumber()))
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new StudentException(ExceptionStatusEnum.STUDENT_NOT_EXIST));

        if (studentInfo.getStudentInfoSource() == null) {
            throw new StudentException(ExceptionStatusEnum.INFO_SOURCE_ERROR);
        }
        if (!studentInfo.getStudentInfoSource().equals(oldStudentInfo.getStudentInfoSource())) {
            studentInfo.setStudentInfoSource(StudentInfoSourceEnum.IMPORT_AND_REGISTER.getCode());
        }
        PropertyReplicator.copyPropertiesIgnoreNull(studentInfo, oldStudentInfo);
        return studentInfoRepository.save(oldStudentInfo);
    }
}
