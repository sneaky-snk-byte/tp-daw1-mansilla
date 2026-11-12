package ar.edu.undec.integrador.biblioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.edu.undec.integrador.biblioteca.model.Libro;
import ar.edu.undec.integrador.biblioteca.service.LibroService;
import jakarta.validation.Valid;

import org.springframework.boot.web.server.autoconfigure.ServerProperties.Servlet;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService){
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listaTodos(){
        return libroService.listarTodos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(libroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Libro> crear(@Valid @RequestBody Libro libro) {
        Libro nuevo = libroService.crear(libro);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro libroActualizado = libroService.actualizar(id, libro);
               
        return ResponseEntity.ok(libroActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

    
}