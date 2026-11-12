package ar.edu.undec.integrador.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.undec.integrador.biblioteca.model.Prestamo;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    int countBySocioIdAndEstadoNot(Long socioId, String estado);

    boolean existsByLibroIdAndEstadoNot(Long libroId, String estado);

    
    
}
