package dev.cyanide.transportcli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TransportCliApplication {

	public static void main(String[] args) {
		// Debug: Print the arguments passed to main
		System.out.println("Main method received args: " + String.join(" ", args));

		// Disable Spring Boot's web environment since this is a CLI app
		SpringApplication app = new SpringApplication(TransportCliApplication.class);
		app.setWebApplicationType(org.springframework.boot.WebApplicationType.NONE);

		System.setProperty("spring.main.web-application-type", "none");
		System.setProperty("logging.level.root", "WARN"); // Reduce Spring Boot startup noise

		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
