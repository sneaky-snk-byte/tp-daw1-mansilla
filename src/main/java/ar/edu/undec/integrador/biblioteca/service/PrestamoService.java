package ar.edu.undec.integrador.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.undec.integrador.biblioteca.model.Prestamo;
import ar.edu.undec.integrador.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService {
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository){
        this.prestamoRepository = prestamoRepository; 
    }

    public List<Prestamo> listarTodos(){
        return prestamoRepository.findAll();

    
    }

    public Prestamo buscarPorId(Long id){
        return prestamoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("prestamo no encontrado."));
    }

    public Prestamo crear(Prestamo prestamo){
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id){
        prestamoRepository.deleteById(id);
    }

    
}    

