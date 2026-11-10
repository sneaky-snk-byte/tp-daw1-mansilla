package ar.edu.undec.integrador.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity 
public class Libro {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(unique = true)
    @NotBlank
    private  String titulo;
    
    @NotBlank
    private  String autor;
    
    @Column(unique = true)
    @NotBlank   
    private  String isbn;

    public Libro() {
    }

    public Libro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
    }


    public String getTitulo() {
        return titulo;
    }


    public String getAutor() {
        return autor;
    }


    public String getIsbn() {
        return isbn;
    }


    public long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public void setAutor(String autor) {
        this.autor = autor;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
    
    

}
