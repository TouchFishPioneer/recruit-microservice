package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.enumeration.AccountStatusEnum;
import cn.herculas.recruit.teacher.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.repository.TeacherAccountRepository;
import cn.herculas.recruit.teacher.service.TeacherAccountService;
import cn.herculas.recruit.teacher.util.generator.KeyGenerator;
import cn.herculas.recruit.teacher.util.replicator.PropertyReplicator;
import org.springframework.stereotype.Service;

@Service
public class TeacherAccountServiceImpl implements TeacherAccountService {

    private final TeacherAccountRepository teacherAccountRepository;

    public TeacherAccountServiceImpl(TeacherAccountRepository teacherAccountRepository) {
        this.teacherAccountRepository = teacherAccountRepository;
    }

    @Override
    public TeacherAccount findTeacherAccount(String teacherUuid) throws TeacherException {
        TeacherAccount teacherAccount = teacherAccountRepository.findByTeacherUuid(teacherUuid);
        if (teacherAccount == null)
            throw new TeacherException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        return teacherAccount;
    }

    @Override
    public TeacherAccount createTeacherAccount(TeacherAccount teacherAccount) throws TeacherException {
        if (teacherAccountRepository.findByTeacherUsername(teacherAccount.getTeacherUsername()) != null)
            throw new TeacherException(ExceptionStatusEnum.TEACHER_ALREADY_EXIST);

        if (teacherAccount.getTeacherUuid() == null)
            teacherAccount.setTeacherUuid(KeyGenerator.uuidGenerator());

        if (teacherAccount.getTeacherStatus() == null)
            teacherAccount.setTeacherStatus(AccountStatusEnum.VALID.getCode());

        return teacherAccountRepository.save(teacherAccount);
    }

    @Override
    public TeacherAccount updateTeacherAccount(TeacherAccount teacherAccount) throws TeacherException {
        TeacherAccount oldTeacherAccount = teacherAccountRepository.findByTeacherUuid(teacherAccount.getTeacherUuid());
        if (oldTeacherAccount == null) {
            throw new TeacherException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        }
        PropertyReplicator.copyPropertiesIgnoreNull(teacherAccount, oldTeacherAccount);
        return teacherAccountRepository.save(oldTeacherAccount);
    }

    @Override
    public TeacherAccount confirmTeacherAccount(String teacherUsername, String teacherPassword) throws TeacherException {
        TeacherAccount teacherAccount = teacherAccountRepository.findByTeacherUsername(teacherUsername);
        if (teacherAccount == null) {
            throw new TeacherException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        }
        if (teacherAccount.getTeacherStatus().equals(AccountStatusEnum.DISABLED.getCode())) {
            throw new TeacherException(ExceptionStatusEnum.STATUS_ERROR);
        }
        if (!teacherAccount.getTeacherPassword().equals(teacherPassword)) {
            throw new TeacherException(ExceptionStatusEnum.PASSWORD_ERROR);
        }
        return teacherAccount;

    }
}
