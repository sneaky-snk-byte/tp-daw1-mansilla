package ar.edu.undec.integrador.biblioteca.controller;

import ar.edu.undec.integrador.biblioteca.repository.SocioRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ar.edu.undec.integrador.biblioteca.model.Prestamo;
import ar.edu.undec.integrador.biblioteca.model.Socio;
import ar.edu.undec.integrador.biblioteca.service.PrestamoService;
import ar.edu.undec.integrador.biblioteca.service.SocioService;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioRepository socioRepository;
    private final SocioService socioService;
    private final PrestamoService prestamoService;
    
    public SocioController(SocioService socioService, SocioRepository socioRepository, PrestamoService prestamoService){
        this.socioService = socioService;
        this.socioRepository = socioRepository;
        this.prestamoService = prestamoService;
    
    }
    @GetMapping
    public List <Socio> listarTodos() {
        return socioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Socio> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(socioService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Socio> crear(@Valid @RequestBody Socio socio) {
        Socio nuevo=  socioService.crear(socio);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(nuevo.getId())
                .toUri();

        return ResponseEntity.created(location).body(nuevo);


    }

    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable Long id,@Valid @RequestBody  Socio socio) {
        Socio socioActualizado = socioService.actualizar(id, socio);

        return ResponseEntity.ok(socioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}/prestamos")
    public ResponseEntity<List<Prestamo>> prestamosDeSocio(@PathVariable Long id) {
        return ResponseEntity.ok(prestamoService.buscarPorSocio(id));
    }
    
    
}
