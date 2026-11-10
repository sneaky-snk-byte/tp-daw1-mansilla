package ar.edu.undec.integrador.Biblioteca.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.undec.integrador.Biblioteca.Model.Libro;

@Repository 
public interface LibroRepository extends JpaRepository<Libro, Long>{

}
