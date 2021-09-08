package br.com.estudo.aluraspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class AluraSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AluraSpringApplication.class, args);
	}

}
