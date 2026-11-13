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

    public PrestamoService(PrestamoRepository prestamoRepository, LibroRepository libroRepository,
            SocioRepository socioRepository) {
        this.prestamoRepository = prestamoRepository;
        this.libroRepository = libroRepository;
        this.socioRepository = socioRepository;
    }

    public List<Prestamo> listarTodos(String estado) {
        if (estado != null && !estado.trim().isEmpty()) {
            return prestamoRepository.findByEstado(estado);
        }
        return prestamoRepository.findAll();
    }

    public List<Prestamo> buscarPorSocio(Long socioId) {
        socioRepository.findById(socioId)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        return prestamoRepository.findBySocioId(socioId);
    }

    public Prestamo buscarPorId(Long id) {
        return prestamoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("prestamo no encontrado."));
    }

    public Prestamo crear(Prestamo prestamo) {

        Libro libro = libroRepository.findById(prestamo.getLibro().getId())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        Socio socio = socioRepository.findById(prestamo.getSocio().getId())
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        if (prestamoRepository.existsByLibroIdAndEstadoNot(libro.getId(), "DEVUELTO")) {
            throw new RuntimeException("El libro ya tiene un prestamo activo.");
        }

        if (prestamoRepository.countBySocioIdAndEstadoNot(
                prestamo.getSocio().getId(), "DEVUELTO") >= 3) {
            throw new RuntimeException("El socio ya tiene 3 prestamos activos");
        }

        prestamo.setLibro(libro);
        prestamo.setSocio(socio);
        return prestamoRepository.save(prestamo);
    }

    public Prestamo cambiarEstado(Long id, String nuevoEstado) {

        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado no puede estar vacío");
        }

        nuevoEstado = nuevoEstado.toUpperCase();
        if (!nuevoEstado.equals("ACTIVO") && !nuevoEstado.equals("DEVUELTO") && !nuevoEstado.equals("VENCIDO")) {
            throw new IllegalArgumentException("Estado inválido. Debe ser ACTIVO, DEVUELTO o VENCIDO");
        }

        Prestamo prestamo = buscarPorId(id);
        String estadoActual = prestamo.getEstado();

        if (estadoActual.equals("DEVUELTO")) {
            throw new RuntimeException("Transición no permitida: DEVUELTO es un estado final");
        }

        if (estadoActual.equals("VENCIDO") && !nuevoEstado.equals("DEVUELTO")) {
            throw new RuntimeException("Transición no permitida: un préstamo VENCIDO solo puede pasar a DEVUELTO");
        }

        if (estadoActual.equals(nuevoEstado)) {
            throw new RuntimeException("Transición no permitida: el préstamo ya se encuentra en estado " + nuevoEstado);
        }

        prestamo.setEstado(nuevoEstado);
        return prestamoRepository.save(prestamo);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }

}
