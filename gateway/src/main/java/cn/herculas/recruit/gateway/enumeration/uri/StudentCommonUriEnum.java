package cn.herculas.recruit.gateway.enumeration.uri;

import lombok.Getter;

@Getter
public enum StudentCommonUriEnum {
    STUDENT_LOGOUT("POST", "/student-log/out"),
    STUDENT_INFO("GET", "/student-account/index"),
    STUDENT_ACCOUNT_DETAIL_UPDATE("PATCH", "/student-account/detail"),
    STUDENT_ACCOUNT_PASSWORD_UPDATE("PATCH", "/student-account/password"),
    CREATE_NEW_QUESTION("POST", "/question/index"),
    VOTE_FOR_QUESTION("POST", "/question/vote");

    private String method;
    private String uri;

    StudentCommonUriEnum(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static boolean contains(String method, String uri) {
        for (StudentCommonUriEnum studentCommonUriEnum : StudentCommonUriEnum.values()) {
            if (method.equals(studentCommonUriEnum.getMethod()) && uri.contains(studentCommonUriEnum.getUri())) {
                return true;
            }
        }
        return false;
    }
}
