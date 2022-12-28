package lhc.group.lhc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LhcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LhcApplication.class, args);
    }

}
