package cn.herculas.recruit.gateway.enumeration.uri;

import lombok.Getter;

@Getter
public enum TeacherCommonUriEnum {
    GET_STUDENT_INFO("GET", "/student-info/index"),
    ;

    private String method;
    private String uri;

    TeacherCommonUriEnum(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static boolean contains(String method, String uri) {
        for (TeacherCommonUriEnum teacherCommonUriEnum : TeacherCommonUriEnum.values()) {
            if (method.equals(teacherCommonUriEnum.getMethod()) && uri.contains(teacherCommonUriEnum.getUri())) {
                return true;
            }
        }
        return false;
    }
}
