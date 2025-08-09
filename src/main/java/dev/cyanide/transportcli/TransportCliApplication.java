package dev.cyanide.transportcli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TransportCliApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TransportCliApplication.class);
		app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);

		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
