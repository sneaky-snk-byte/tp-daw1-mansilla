package ar.edu.undec.integrador.Biblioteca.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.undec.integrador.Biblioteca.Model.Socio;
import ar.edu.undec.integrador.Biblioteca.Service.SocioService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/socios")
public class SocioController {

    private final SocioService socioService;

    public SocioController(SocioService socioService){
        this.socioService = socioService;
    
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
    public ResponseEntity<Socio> crear(@RequestBody Socio socio) {
        Socio nuevo=  socioService.crear(socio);
        return ResponseEntity.status(201).body(nuevo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        socioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
    
}
