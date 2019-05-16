package cn.herculas.recruit.gateway.enumeration;

import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Getter
public enum PermissionEnum {
    LIST_STUDENT_INFO(HttpMethod.GET, "/student-info/list", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    GET_STUDENT_INFO(HttpMethod.GET, "/student-info/index", Arrays.asList(RoleEnum.STUDENT.getCode(), RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    CREATE_STUDENT_INFO(HttpMethod.POST, "/student-info/index", Arrays.asList(RoleEnum.STUDENT.getCode(), RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    UPDATE_STUDENT_INFO(HttpMethod.PATCH, "/student-info/index", Arrays.asList(RoleEnum.STUDENT.getCode(), RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    LIST_TEACHER_INFO(HttpMethod.GET, "/teacher-info/list", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode())),
    GET_TEACHER_INFO(HttpMethod.GET, "/teacher-info/index", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    CREATE_TEACHER_INFO(HttpMethod.POST, "/teacher-info/index", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    UPDATE_TEACHER_INFO(HttpMethod.PATCH, "/teacher-info/index", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    GET_STUDENT_ACCOUNT(HttpMethod.GET, "/student-account/index", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),
    CREATE_STUDENT_ACCOUNT(HttpMethod.POST, "/student-account/index", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),
    UPDATE_STUDENT_ACCOUNT(HttpMethod.PATCH, "/student-account/detail", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),
    UPDATE_STUDENT_PASSWORD(HttpMethod.PATCH, "/student-account/password", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),

    GET_TEACHER_ACCOUNT(HttpMethod.GET, "/teacher-account/index", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    CREATE_TEACHER_ACCOUNT(HttpMethod.POST, "/teacher-account/index", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),
    UPDATE_TEACHER_ACCOUNT(HttpMethod.PATCH, "/teacher-account/detail", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    UPDATE_TEACHER_PASSWORD(HttpMethod.PATCH, "/teacher-account/password", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    GET_CAPTCHA_KEY(HttpMethod.GET, "/captcha/key", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),
    GET_CAPTCHA_IMAGE(HttpMethod.GET, "/captcha/image", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),

    STUDENT_LOGIN(HttpMethod.POST, "/student-log/in", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),
    STUDENT_LOGOUT(HttpMethod.POST, "/student-log/out", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),

    TEACHER_LOGIN(HttpMethod.POST, "/teacher-log/in", Collections.singletonList(RoleEnum.NO_DEMAND.getCode())),
    TEACHER_LOGOUT(HttpMethod.POST, "/teacher-log/out", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    LIST_QUESTION(HttpMethod.GET, "/question/list", Arrays.asList(RoleEnum.STUDENT.getCode(), RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    CREATE_QUESTION(HttpMethod.POST, "/question/index", Collections.singletonList(RoleEnum.STUDENT.getCode())),
    REVIEW_QUESTION(HttpMethod.PATCH, "/question/review", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    VOTE_FOR_QUESTION(HttpMethod.POST, "/question/vote", Collections.singletonList(RoleEnum.STUDENT.getCode())),
    ANSWER_QUESTION(HttpMethod.PATCH, "/question/answer", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    LIST_NOTIFICATION(HttpMethod.GET, "/notification/list", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),
    CREATE_NOTIFICATION(HttpMethod.POST, "/notification/index", Arrays.asList(RoleEnum.TEACHER_ADMINISTRATOR.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_LEADER.getCode(), RoleEnum.TEACHER_ADMISSION_TEAM_MEMBER.getCode())),

    GET_REGION_STATISTICS(HttpMethod.GET, "/visualization/region", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),
    GET_DIVISION_STATISTICS(HttpMethod.GET, "/visualization/division", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),
    GET_CONTACT_STATISTICS(HttpMethod.GET, "/visualization/contact", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode())),
    GET_GRADE_STATISTICS(HttpMethod.GET, "/visualization/grade", Collections.singletonList(RoleEnum.TEACHER_ADMINISTRATOR.getCode()));

    private HttpMethod method;
    private String uri;
    private List<Integer> roles;

    PermissionEnum(HttpMethod method, String uri, List<Integer> roles) {
        this.method = method;
        this.uri = uri;
        this.roles = roles;
    }

    public static List<Integer> rolePermitted(String method, String uri) {
        for (PermissionEnum permissionEnum : PermissionEnum.values()) {
            if (permissionEnum.getMethod().matches(method) && uri.contains(permissionEnum.getUri())) {
                return permissionEnum.getRoles();
            }
        }
        return null;
    }
}
