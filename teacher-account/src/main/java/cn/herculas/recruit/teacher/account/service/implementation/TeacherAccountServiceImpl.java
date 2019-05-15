package cn.herculas.recruit.teacher.account.service.implementation;

import cn.herculas.recruit.teacher.account.client.SessionClient;
import cn.herculas.recruit.teacher.account.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.account.enumeration.AccountStatusEnum;
import cn.herculas.recruit.teacher.account.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.teacher.account.exception.TeacherAccountException;
import cn.herculas.recruit.teacher.account.repository.TeacherAccountRepository;
import cn.herculas.recruit.teacher.account.service.TeacherAccountService;
import cn.herculas.recruit.teacher.account.util.generator.KeyGenerator;
import cn.herculas.recruit.teacher.account.util.proxy.CookieProxy;
import cn.herculas.recruit.teacher.account.util.replicator.PropertyReplicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class TeacherAccountServiceImpl implements TeacherAccountService {

    @Value("${cookie.key}")
    private String key;

    private final TeacherAccountRepository teacherAccountRepository;
    private final SessionClient sessionClient;

    public TeacherAccountServiceImpl(TeacherAccountRepository teacherAccountRepository, SessionClient sessionClient) {
        this.teacherAccountRepository = teacherAccountRepository;
        this.sessionClient = sessionClient;
    }

    @Override
    public TeacherAccount confirmTeacherAccount(String teacherUsername, String teacherPassword) {
        TeacherAccount teacherAccount = teacherAccountRepository.findByTeacherUsername(teacherUsername);

        if (teacherAccount == null)
            return null;
        else if (teacherAccount.getTeacherStatus().equals(AccountStatusEnum.DISABLED.getCode()))
            return null;
        else if (!teacherAccount.getTeacherPassword().equals(teacherPassword)) {
            return null;
        } else {
            return teacherAccount;
        }
    }

    @Override
    public TeacherAccount createTeacherAccount(TeacherAccount teacherAccount) {
        if (teacherAccountRepository.findByTeacherUsername(teacherAccount.getTeacherUsername()) != null)
            throw new TeacherAccountException(ExceptionStatusEnum.TEACHER_ALREADY_EXIST);

        if (teacherAccount.getTeacherUuid() == null)
            teacherAccount.setTeacherUuid(KeyGenerator.uuidGenerator());

        if (teacherAccount.getTeacherStatus() == null)
            teacherAccount.setTeacherStatus(AccountStatusEnum.VALID.getCode());

        return teacherAccountRepository.save(teacherAccount);
    }

    @Override
    public TeacherAccount updateTeacherAccount(TeacherAccount teacherAccount) {
        TeacherAccount oldTeacherAccount = teacherAccountRepository.findByTeacherUuid(teacherAccount.getTeacherUuid());
        if (oldTeacherAccount == null)
            throw new TeacherAccountException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        PropertyReplicator.copyPropertiesIgnoreNull(teacherAccount, oldTeacherAccount);
        return teacherAccountRepository.save(oldTeacherAccount);
    }

    @Override
    public TeacherAccount findTeacherAccountByUuid(String teacherUuid) {
        TeacherAccount teacherAccount = teacherAccountRepository.findByTeacherUuid(teacherUuid);
        if (teacherAccount == null)
            throw new TeacherAccountException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        return teacherAccount;
    }

    @Override
    public TeacherAccount findTeacherAccountByCookie(HttpServletRequest httpServletRequest) {
        Cookie cookie = CookieProxy.cookieExtractor(httpServletRequest, key);
        Map<String, String> sessionContent = sessionClient.findSession(cookie.getValue());
        if (sessionContent == null) {
            throw new TeacherAccountException(ExceptionStatusEnum.SESSION_NOT_EXIST);
        }
        String teacherUuid;
        if (sessionContent.containsKey("uuid")) {
            teacherUuid = sessionContent.get("uuid");
        } else {
            throw new TeacherAccountException(ExceptionStatusEnum.SESSION_NOT_EXIST);
        }
        return this.findTeacherAccountByUuid(teacherUuid);
    }
}
