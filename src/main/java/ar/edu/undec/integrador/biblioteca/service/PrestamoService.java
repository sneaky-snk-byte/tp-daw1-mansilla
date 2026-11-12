package ar.edu.undec.integrador.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.undec.integrador.biblioteca.model.Libro;
import ar.edu.undec.integrador.biblioteca.model.Prestamo;
import ar.edu.undec.integrador.biblioteca.repository.LibroRepository;
import ar.edu.undec.integrador.biblioteca.repository.PrestamoRepository;
import ar.edu.undec.integrador.biblioteca.repository.SocioRepository;
import ar.edu.undec.integrador.biblioteca.model.Socio;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;
    private final LibroRepository libroRepository;
    private final SocioRepository socioRepository;


    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository, SocioRepository socioRepository){
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.socioRepository = socioRepository;
    }

    public List<Prestamo> listarTodos(){
        return prestamoRepository.findAll();

    
    }

    public Prestamo buscarPorId(Long id){
        return prestamoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("prestamo no encontrado."));
    }

    public Prestamo crear(Prestamo prestamo){

        Libro libro = libroRepository.findById(prestamo.getLibro().getId())
        .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Socio socio = socioRepository.findById(prestamo.getSocio().getId())
        .orElseThrow(()-> new RuntimeException("Socio no encontrado"));


        if (prestamoRepository.existsByLibroIdAndEstadoNot(libro.getId(), "DEVUELTO")){
            throw new RuntimeException("El libro ya tiene un prestamo activo.");
        }

        if (prestamoRepository.countBySocioIdAndEstadoNot(
            prestamo.getSocio().getId(), "DEVUELTO") >= 3){
                throw new RuntimeException("El socio ya tiene 3 prestamos activos");
            }

        prestamo.setLibro(libro);
        prestamo.setSocio(socio);
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id){
        prestamoRepository.deleteById(id);
    }

    
}    

