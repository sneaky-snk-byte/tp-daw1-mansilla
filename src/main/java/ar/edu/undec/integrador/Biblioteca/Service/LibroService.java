package ar.edu.undec.integrador.Biblioteca.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.undec.integrador.Biblioteca.Model.Libro;
import ar.edu.undec.integrador.Biblioteca.Repository.LibroRepository;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository){
        this.libroRepository = libroRepository;
    }

    public List<Libro> listarTodos(){
        return libroRepository.findAll();

    }

    public Libro buscarPorId(Long id){
        return libroRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Libro no encontrado."));
    }

    public Libro crear(Libro libro){
        return libroRepository.save(libro);
    }

    public void eliminar(Long id){
        libroRepository.deleteById(id);
    }

    
}