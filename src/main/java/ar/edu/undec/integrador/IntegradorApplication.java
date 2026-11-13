package ar.edu.undec.integrador;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ar.edu.undec.integrador.biblioteca.model.Libro;
import ar.edu.undec.integrador.biblioteca.model.Socio;
import ar.edu.undec.integrador.biblioteca.repository.LibroRepository;
import ar.edu.undec.integrador.biblioteca.repository.SocioRepository;

@SpringBootApplication
public class IntegradorApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegradorApplication.class, args);
	}


}


