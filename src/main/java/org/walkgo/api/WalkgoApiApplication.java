package org.walkgo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.walkgo.api.model.Usuario;
import org.walkgo.api.repository.UsuarioRepository;

@SpringBootApplication
public class WalkgoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalkgoApiApplication.class, args);
	}

}
