package cn.herculas.recruit.teacher.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class TeacherAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeacherAccountApplication.class, args);
    }
}
