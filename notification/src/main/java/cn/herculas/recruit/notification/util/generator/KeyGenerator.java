package cn.herculas.recruit.notification.util.generator;

import java.util.UUID;

public class KeyGenerator {
    public static String uuidGenerator() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
