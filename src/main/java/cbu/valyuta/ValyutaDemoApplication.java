package cbu.valyuta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ValyutaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValyutaDemoApplication.class, args);
	}

}
