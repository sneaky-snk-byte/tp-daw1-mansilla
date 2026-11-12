package ar.edu.undec.integrador.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.undec.integrador.biblioteca.model.Socio;

@Repository
public interface SocioRepository  extends JpaRepository<Socio, Long>{
        boolean existsByEmail(String email);
}
