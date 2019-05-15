package cn.herculas.recruit.gateway.enumeration.uri;

import lombok.Getter;

@Getter
public enum CommonUriEnum {
    CREATE_STUDENT_INFO("POST", "/student-info/index"),
    UPDATE_STUDENT_INFO("PATCH", "/student-info/index");

    private String method;
    private String uri;

    CommonUriEnum(String method, String uri) {
        this.method = method;
        this.uri = uri;
    }

    public static boolean contains(String method, String uri) {
        for (CommonUriEnum commonUriEnum : CommonUriEnum.values()) {
            if (method.equals(commonUriEnum.getMethod()) && uri.contains(commonUriEnum.getUri())) {
                return true;
            }
        }
        return false;
    }
}
