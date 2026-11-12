package ar.edu.undec.integrador.biblioteca.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.edu.undec.integrador.biblioteca.model.Prestamo;
import ar.edu.undec.integrador.biblioteca.service.PrestamoService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService){
        this.prestamoService = prestamoService;

    }
    @GetMapping
    public List<Prestamo> listaTodos(@RequestParam(required = false) String estado){
        return prestamoService.listarTodos(estado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Prestamo> crear(@Valid @RequestBody Prestamo prestamo) {
        Prestamo nuevo = prestamoService.crear(prestamo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Prestamo> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> body){
        String nuevoEstado = body.get("estado");
        return ResponseEntity.ok(prestamoService.cambiarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    

    
}
