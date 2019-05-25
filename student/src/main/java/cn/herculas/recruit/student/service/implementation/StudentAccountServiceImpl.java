package cn.herculas.recruit.student.service.implementation;

import cn.herculas.recruit.student.data.DO.StudentAccount;
import cn.herculas.recruit.student.enumeration.AccountStatusEnum;
import cn.herculas.recruit.student.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.student.exception.StudentException;
import cn.herculas.recruit.student.repository.StudentAccountRepository;
import cn.herculas.recruit.student.service.StudentAccountService;
import cn.herculas.recruit.student.util.generator.KeyGenerator;
import cn.herculas.recruit.student.util.replicator.PropertyReplicator;
import org.springframework.stereotype.Service;

@Service
public class StudentAccountServiceImpl implements StudentAccountService {

    private final StudentAccountRepository studentAccountRepository;

    public StudentAccountServiceImpl(StudentAccountRepository studentAccountRepository) {
        this.studentAccountRepository = studentAccountRepository;
    }

    @Override
    public StudentAccount findStudentAccount(String studentUuid) throws StudentException {
        StudentAccount studentAccount = studentAccountRepository.findByStudentUuid(studentUuid);
        if (studentAccount == null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        }
        return studentAccount;
    }

    @Override
    public StudentAccount createStudentAccount(StudentAccount studentAccount) throws StudentException {
        if (studentAccountRepository.findByStudentEmail(studentAccount.getStudentEmail()) != null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_ALREADY_EXIST);
        }
        if (studentAccount.getStudentUuid() == null) {
            studentAccount.setStudentUuid(KeyGenerator.uuidGenerator());
        }
        if (studentAccount.getStudentStatus() == null) {
            studentAccount.setStudentStatus(AccountStatusEnum.VALID.getCode());
        }
        return studentAccountRepository.save(studentAccount);
    }

    @Override
    public StudentAccount updateStudentAccount(StudentAccount studentAccount) throws StudentException {
        StudentAccount oldStudentAccount = studentAccountRepository.findByStudentUuid(studentAccount.getStudentUuid());
        if (oldStudentAccount == null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        }
        PropertyReplicator.copyPropertiesIgnoreNull(studentAccount, oldStudentAccount);
        return studentAccountRepository.save(oldStudentAccount);
    }

    @Override
    public StudentAccount confirmStudentAccount(String studentEmail, String studentPassword) throws StudentException {
        StudentAccount studentAccount = studentAccountRepository.findByStudentEmail(studentEmail);
        if (studentAccount == null) {
            throw new StudentException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        }
        if (studentAccount.getStudentStatus().equals(AccountStatusEnum.DISABLED.getCode())) {
            throw new StudentException(ExceptionStatusEnum.STATUS_ERROR);
        }
        if (!studentAccount.getStudentPassword().equals(studentPassword)) {
            throw new StudentException(ExceptionStatusEnum.PASSWORD_ERROR);
        }
        return studentAccount;
    }
}
