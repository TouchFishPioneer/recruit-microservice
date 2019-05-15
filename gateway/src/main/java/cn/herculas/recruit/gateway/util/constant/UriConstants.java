package cn.herculas.recruit.gateway.util.constant;

public final class UriConstants {

    // Prefixes

    private static final String STUDENT_INFORMATION_SERVICE_PREFIX = "/student-info";
    private static final String TEACHER_INFORMATION_SERVICE_PREFIX = "/teacher-info";
    private static final String STUDENT_LOG_SERVICE_PREFIX = "/student-log";
    private static final String TEACHER_LOG_SERVICE_PREFIX = "/teacher-log";
    private static final String STUDENT_ACCOUNT_MANAGEMENT_SERVICE_PREFIX = "/student-account";
    private static final String TEACHER_ACCOUNT_MANAGEMENT_SERVICE_PREFIX = "/teacher-account";
    private static final String QUESTION_SERVICE_PREFIX = "/question";
    private static final String NOTIFICATION_SERVICE_PREFIX = "/notification";

    // functional

    private static final String COMMON = "/index";

    private static final String LOGIN = "/in";
    private static final String LOGOUT = "/out";
    private static final String INFO = "/info";

    private static final String CAPTCHA_KEY = "/key";
    private static final String CAPTCHA_IMAGE = "/image";

    private static final String PASSWORD = "/password";

    private static final String QUESTION_REVIEW = "/review";
    private static final String QUESTION_VOTE = "/vote";
    private static final String QUESTION_ANSWER = "/answer";

    // Specific URI

    // student information

    public static final String GET_STUDENT_INFORMATION = STUDENT_INFORMATION_SERVICE_PREFIX + COMMON;

    // student log

    public static final String STUDENT_LOGIN = STUDENT_LOG_SERVICE_PREFIX + LOGIN;
    public static final String STUDENT_LOGOUT = STUDENT_LOG_SERVICE_PREFIX + LOGOUT;

    // teacher log

    public static final String TEACHER_LOGIN = TEACHER_LOG_SERVICE_PREFIX + LOGIN;
    public static final String TEACEHR_LOGOUT = TEACHER_LOG_SERVICE_PREFIX + LOGOUT;
}
