package br.com.gastos.gastosapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GastoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GastoApiApplication.class, args);
	}
}
