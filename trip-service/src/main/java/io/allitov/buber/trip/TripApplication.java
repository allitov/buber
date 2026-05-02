package io.allitov.buber.trip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TripApplication {

    static void main() {
        SpringApplication.run(TripApplication.class);
    }
}
