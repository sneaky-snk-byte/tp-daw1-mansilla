package ar.edu.undec.integrador.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.undec.integrador.biblioteca.model.Libro;

@Repository 
public interface LibroRepository extends JpaRepository<Libro, Long>{

}
