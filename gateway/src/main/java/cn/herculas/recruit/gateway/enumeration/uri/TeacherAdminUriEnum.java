package cn.herculas.recruit.gateway.enumeration.uri;

import lombok.Getter;

@Getter
public enum TeacherAdminUriEnum {
    ;

    private String method;
    private String uri;

    TeacherAdminUriEnum(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static boolean contains(String method, String uri) {
        for (TeacherAdminUriEnum teacherAdminUriEnum : TeacherAdminUriEnum.values()) {
            if (method.equals(teacherAdminUriEnum.getMethod()) && uri.contains(teacherAdminUriEnum.getUri())) {
                return true;
            }
        }
        return false;
    }
}
