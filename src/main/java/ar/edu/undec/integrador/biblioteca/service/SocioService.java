package ar.edu.undec.integrador.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.undec.integrador.biblioteca.model.Socio;
import ar.edu.undec.integrador.biblioteca.repository.SocioRepository;

@Service
public class SocioService {
    private final SocioRepository socioRepository;

    public SocioService(SocioRepository socioRepository) {
        this.socioRepository = socioRepository;

    }

    public List<Socio> listarTodos() {
        return socioRepository.findAll();

    }

    public Socio buscarPorId(Long id) {
        return socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("socio no encontrado."));
    }

    public Socio crear(Socio socio) {
        if (socioRepository.existsByEmail(socio.getEmail())) {
            throw new RuntimeException("ese socio ya esta registrado");
        }
        return socioRepository.save(socio);

    }

    public Socio actualizar(Long id, Socio datos) {
        Socio socio = buscarPorId(id);
        socio.setNombre(datos.getNombre());
        socio.setEmail(datos.getEmail());
        return socioRepository.save(socio);
    }

    public void eliminar(Long id) {
        socioRepository.deleteById(id);
    }
}
