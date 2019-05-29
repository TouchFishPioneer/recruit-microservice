package cn.herculas.recruit.teacher.repository;

import cn.herculas.recruit.teacher.data.DO.TeacherAccount;
import cn.herculas.recruit.teacher.data.DTO.TeacherRegionStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherAccountRepository extends JpaRepository<TeacherAccount, Integer> {
    TeacherAccount findByTeacherUsername(String teacherUsername);
    TeacherAccount findByTeacherUuid(String teacherUuid);

    @Query("SELECT NEW cn.herculas.recruit.teacher.data.DTO.TeacherRegionStatisticsDTO(ta.teacherRegion, COUNT(ta)) " +
            "FROM cn.herculas.recruit.teacher.data.DO.TeacherAccount ta " +
            "GROUP BY ta.teacherRegion")
    List<TeacherRegionStatisticsDTO> groupByTeacherRegion();
}
