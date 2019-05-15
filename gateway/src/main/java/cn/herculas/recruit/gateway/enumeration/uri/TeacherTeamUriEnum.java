package cn.herculas.recruit.gateway.enumeration.uri;

import lombok.Getter;

@Getter
public enum TeacherTeamUriEnum {
    ;

    private String method;
    private String uri;

    TeacherTeamUriEnum(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static boolean contains(String method, String uri) {
        for (TeacherTeamUriEnum teacherTeamUriEnum : TeacherTeamUriEnum.values()) {
            if (method.equals(teacherTeamUriEnum.getMethod()) && uri.contains(teacherTeamUriEnum.getUri())) {
                return true;
            }
        }
        return false;
    }
}
