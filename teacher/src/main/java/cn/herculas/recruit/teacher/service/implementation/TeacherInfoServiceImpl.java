package cn.herculas.recruit.teacher.service.implementation;

import cn.herculas.recruit.teacher.data.DO.TeacherInfo;
import cn.herculas.recruit.teacher.enumeration.ExceptionStatusEnum;
import cn.herculas.recruit.teacher.exception.TeacherException;
import cn.herculas.recruit.teacher.repository.TeacherInfoRepository;
import cn.herculas.recruit.teacher.service.TeacherInfoService;
import cn.herculas.recruit.teacher.util.generator.KeyGenerator;
import cn.herculas.recruit.teacher.util.replicator.PropertyReplicator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Service
public class TeacherInfoServiceImpl implements TeacherInfoService {

    private final TeacherInfoRepository teacherInfoRepository;

    public TeacherInfoServiceImpl(TeacherInfoRepository teacherInfoRepository) {
        this.teacherInfoRepository = teacherInfoRepository;
    }

    @Override
    public Page<TeacherInfo> listTeacherInfo(Pageable pageable) {
        return teacherInfoRepository.findAll(pageable);
    }

    @Override
    public TeacherInfo findTeacherInfo(String teacherUuid) throws TeacherException {
        TeacherInfo teacherInfo = teacherInfoRepository.findByTeacherUuid(teacherUuid);
        if (teacherInfo == null) {
            throw new TeacherException(ExceptionStatusEnum.TEACHER_NOT_EXIST);
        }
        return teacherInfo;
    }

    @Override
    public TeacherInfo createTeacherInfo(TeacherInfo teacherInfo) throws TeacherException {
        if (teacherInfoRepository.findByTeacherUuid(teacherInfo.getTeacherUuid()) != null ||
                teacherInfoRepository.findByTeacherCardNumber(teacherInfo.getTeacherCardNumber()) != null) {
            throw new TeacherException(ExceptionStatusEnum.TEACHER_ALREADY_EXIST);
        }
        if (teacherInfo.getTeacherUuid() == null) {
            teacherInfo.setTeacherUuid(KeyGenerator.uuidGenerator());
        }
        return teacherInfoRepository.save(teacherInfo);
    }

    @Override
    public TeacherInfo updateTeacherInfo(TeacherInfo teacherInfo) throws TeacherException {
        TeacherInfo oldTeacherInfo = Stream.<Supplier<TeacherInfo>>of(
                () -> teacherInfoRepository.findByTeacherUuid(teacherInfo.getTeacherUuid()),
                () -> teacherInfoRepository.findByTeacherCardNumber(teacherInfo.getTeacherCardNumber()))
                .map(Supplier::get)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new TeacherException(ExceptionStatusEnum.TEACHER_NOT_EXIST));
        PropertyReplicator.copyPropertiesIgnoreNull(teacherInfo, oldTeacherInfo);
        return teacherInfoRepository.save(oldTeacherInfo);
    }
}
