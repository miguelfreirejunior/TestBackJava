package br.com.gastos.gastosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableOAuth2Sso
public class GastoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastoApiApplication.class, args);
	}
}
