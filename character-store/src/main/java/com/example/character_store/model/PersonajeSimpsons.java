package com.example.character_store.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SIMPSONS")
public class PersonajeSimpsons extends Producto{

    private Integer edad;

    @Column(length = 40)
    private String genero;

    @Column(length = 300)
    private String ocupacion;

    @Column(length = 40)
    private String estado;

    public PersonajeSimpsons() {}

    @Override
    public String getDescripcionVenta() {
        String texto = (ocupacion == null || ocupacion.isBlank())
                ? "Personaje de Springfield"
                : ocupacion;
        return getNombre() + " - " + texto;
    }

    @Override
    public String getAtributoPrincipal() {
        return (ocupacion == null || ocupacion.isBlank())
                ? "Sin ocupación registrada"
                : ocupacion;
    }

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

    // Generar getters y setters.
    



	
}
