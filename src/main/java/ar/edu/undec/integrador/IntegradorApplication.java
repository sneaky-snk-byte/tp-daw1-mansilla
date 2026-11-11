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

@Bean
CommandLineRunner datos(LibroRepository libros, SocioRepository socios) {
    return args -> {
                libros.save(new Libro("El principito", "Saint-Exupéry", "978-987-01-0001-1"));
        libros.save(new Libro("Cien años de soledad", "García Márquez", "978-987-01-0002-2"));
        libros.save(new Libro("1984", "George Orwell", "978-987-01-0003-3"));
        libros.save(new Libro("Rayuela", "Julio Cortázar", "978-987-01-0004-4"));
        libros.save(new Libro("Ficciones", "Jorge Luis Borges", "978-987-01-0005-5"));
        libros.save(new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "978-987-01-0006-6"));
        libros.save(new Libro("Crónica de una muerte anunciada", "García Márquez", "978-987-01-0007-7"));
        libros.save(new Libro("Fahrenheit 451", "Ray Bradbury", "978-987-01-0008-8"));
        libros.save(new Libro("El túnel", "Ernesto Sabato", "978-987-01-0009-9"));
        libros.save(new Libro("La sombra del viento", "Carlos Ruiz Zafón", "978-987-01-0010-0"));

        socios.save(new Socio("Juan Torres", "juan@mail.com"));
        socios.save(new Socio("Ana López", "ana@mail.com"));
        socios.save(new Socio("Carlos Gómez", "carlos@mail.com"));
        socios.save(new Socio("María Fernández", "maria@mail.com"));
        socios.save(new Socio("Lucas Díaz", "lucas@mail.com"));
        socios.save(new Socio("Sofía Martínez", "sofia@mail.com"));
        socios.save(new Socio("Diego Rodríguez", "diego@mail.com"));
        socios.save(new Socio("Laura Pérez", "laura@mail.com"));
        socios.save(new Socio("Mateo Sánchez", "mateo@mail.com"));
        socios.save(new Socio("Valentina Romero", "valentina@mail.com"));

    };
}

}


