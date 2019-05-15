package cn.herculas.recruit.teacher.information.service.implementation;

import cn.herculas.recruit.teacher.information.data.DO.TeacherInformation;
import cn.herculas.recruit.teacher.information.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.teacher.information.service.TeacherInformationService;
import cn.herculas.recruit.teacher.information.util.generator.KeyGenerator;
import cn.herculas.recruit.teacher.information.util.replicator.PropertyReplicator;
import cn.herculas.recruit.teacher.information.exception.TeacherInformationException;
import cn.herculas.recruit.teacher.information.repository.TeacherInformationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeacherInformationServiceImpl implements TeacherInformationService {

    private final TeacherInformationRepository teacherInformationRepository;

    public TeacherInformationServiceImpl(TeacherInformationRepository teacherInformationRepository) {
        this.teacherInformationRepository = teacherInformationRepository;
    }

    @Override
    public TeacherInformation createTeacherInformation(TeacherInformation teacherInformation) {
        if (teacherInformationRepository.findByTeacherCardNumber(teacherInformation.getTeacherCardNumber()) != null)
            throw new TeacherInformationException(ExceptionStatusEnum.TEACHER_ALREADY_EXIST);

        if (teacherInformation.getTeacherUuid() == null)
            teacherInformation.setTeacherUuid(KeyGenerator.uuidGenerator());

        return teacherInformationRepository.save(teacherInformation);
    }

    @Override
    public TeacherInformation updateTeacherInformation(TeacherInformation teacherInformation) {
        TeacherInformation oldTeacherInformation = teacherInformationRepository.findByTeacherUuid(teacherInformation.getTeacherUuid());
        if (oldTeacherInformation == null)
            throw new TeacherInformationException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        PropertyReplicator.copyPropertiesIgnoreNull(teacherInformation, oldTeacherInformation);
        return teacherInformationRepository.save(oldTeacherInformation);
    }

    @Override
    public Page<TeacherInformation> listTeacherInformation(Pageable pageable) {
        return teacherInformationRepository.findAll(pageable);
    }

    @Override
    public TeacherInformation findTeacherInformation(String teacherUuid) {
        TeacherInformation teacherInformation = teacherInformationRepository.findByTeacherUuid(teacherUuid);
        if (teacherInformation == null)
            throw new TeacherInformationException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        return teacherInformation;
    }
}
