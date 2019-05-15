package cn.herculas.recruit.student.account.service.implementation;

import cn.herculas.recruit.student.account.data.DO.StudentAccount;
import cn.herculas.recruit.student.account.enumeration.AccountStatusEnum;
import cn.herculas.recruit.student.account.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.student.account.exception.StudentAccountException;
import cn.herculas.recruit.student.account.repository.StudentAccountRepository;
import cn.herculas.recruit.student.account.service.StudentAccountService;
import cn.herculas.recruit.student.account.util.generator.KeyGenerator;
import cn.herculas.recruit.student.account.util.replicator.PropertyReplicator;
import org.springframework.stereotype.Service;

@Service
public class StudentAccountServiceImpl implements StudentAccountService {

    private final StudentAccountRepository studentAccountRepository;

    public StudentAccountServiceImpl(StudentAccountRepository studentAccountRepository) {
        this.studentAccountRepository = studentAccountRepository;
    }

    @Override
    public StudentAccount createStudentAccount(StudentAccount studentAccount) {
        if (studentAccountRepository.findByStudentEmail(studentAccount.getStudentEmail()) != null)
            throw new StudentAccountException(ExceptionStatusEnum.STUDENT_ALREADY_EXIST);

        if (studentAccount.getStudentUuid() == null)
            studentAccount.setStudentUuid(KeyGenerator.uuidGenerator());

        if (studentAccount.getStudentStatus() == null)
            studentAccount.setStudentStatus(AccountStatusEnum.VALID.getCode());

        return studentAccountRepository.save(studentAccount);
    }

    @Override
    public StudentAccount updateStudentAccount(StudentAccount studentAccount) {
        StudentAccount oldStudentAccount = studentAccountRepository.findByStudentUuid(studentAccount.getStudentUuid());
        if (oldStudentAccount == null)
            throw new StudentAccountException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        PropertyReplicator.copyPropertiesIgnoreNull(studentAccount, oldStudentAccount);
        return studentAccountRepository.save(oldStudentAccount);
    }

    @Override
    public StudentAccount findStudentAccountByUuid(String studentUuid) {
        StudentAccount studentAccount = studentAccountRepository.findByStudentUuid(studentUuid);
        if (studentAccount == null)
            throw new StudentAccountException(ExceptionStatusEnum.STUDENT_NOT_EXIST);
        return studentAccount;
    }
}
