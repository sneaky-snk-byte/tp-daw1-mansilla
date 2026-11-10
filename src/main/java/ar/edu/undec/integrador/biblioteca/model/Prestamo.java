package ar.edu.undec.integrador.biblioteca.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity 
public class Prestamo{    

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "libro_id"
    )
    private Libro libro;
    
    @ManyToOne
    @JoinColumn(
        name = "socio_id"
    )
    private Socio socio;

    private LocalDate fechaDevolucion;

    private String estado = "ACTIVO";


    
   
 
    public Prestamo() {
    }


    


    public Prestamo(Libro libro, Socio socio, LocalDate fechaDevolucion) {
        this.libro = libro;
        this.socio = socio;
        this.fechaDevolucion = fechaDevolucion;
    }





    public Long getId() {
        return id;
    }





    public Libro getLibro() {
        return libro;
    }





    public Socio getSocio() {
        return socio;
    }





    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }





    public String getEstado() {
        return estado;
    }





    public void setId(Long id) {
        this.id = id;
    }





    public void setLibro(Libro libro) {
        this.libro = libro;
    }





    public void setSocio(Socio socio) {
        this.socio = socio;
    }





    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }





    public void setEstado(String estado) {
        this.estado = estado;
    }

    





    
}