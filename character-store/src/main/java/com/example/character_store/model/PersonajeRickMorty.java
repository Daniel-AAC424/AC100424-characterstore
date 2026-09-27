package com.example.character_store.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RICK_MORTY")
public class PersonajeRickMorty extends Producto {
	
    @Column(name = "especie", length = 80)
    private String especie;

    @Column(name = "tipo", length = 180)
    private String tipo;

    @Column(name = "origen", length = 180)
    private String origen;

    @Column(name = "localizacion", length = 180)
    private String localizacion;

    @Column(name = "genero", length = 40)
    private String genero;

    @Column(name = "estado", length = 40)
    private String estado;

    public PersonajeRickMorty() {}

    @Override
    public String getDescripcionVenta() {
        StringBuilder sb = new StringBuilder(getNombre());
        if (origen != null && !origen.isBlank()) {
            sb.append(" - Origen: ").append(origen);
        }
        return sb.toString();
    }

    @Override
    public String getAtributoPrincipal() {
        boolean hayEspecie = especie != null && !especie.isBlank();
        boolean hayTipo = tipo != null && !tipo.isBlank();

        if (hayEspecie && hayTipo) {
            return especie + " (" + tipo + ")";
        }
        if (hayEspecie) {
            return especie;
        }
        if (hayTipo) {
            return tipo;
        }
        return "Especie no registrada";
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
