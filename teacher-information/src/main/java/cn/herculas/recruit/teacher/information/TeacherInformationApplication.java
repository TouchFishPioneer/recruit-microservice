package cn.herculas.recruit.teacher.information;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TeacherInformationApplication {
    public static void main(String[] args) {
        SpringApplication.run(TeacherInformationApplication.class, args);
    }
}
