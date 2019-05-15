package cn.herculas.recruit.student.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class StudentAccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentAccountApplication.class, args);
    }
}
