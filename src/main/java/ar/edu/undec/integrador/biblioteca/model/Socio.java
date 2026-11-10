package ar.edu.undec.integrador.biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity 
public class Socio {

    @Id 
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Long id;
    
    @NotBlank
    private  String nombre;
    
    @Email
    @NotBlank
    private  String email;
    
    public Socio() {
    }

    public Socio(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
  
    

    public String getNombre() {
        return nombre;
    }      
    public String getEmail() {
        return email;
    }
    public Long getId() {
            return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    
}
