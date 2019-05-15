package cn.herculas.recruit.student.account.util.generator;

import java.util.UUID;

public class KeyGenerator {
    public static String uuidGenerator() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
