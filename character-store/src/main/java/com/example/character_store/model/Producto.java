package com.example.character_store.model;

import java.math.BigDecimal;
import jakarta.persistence.*;

@Entity
@Table(name = "productos",
       uniqueConstraints = @UniqueConstraint(
           name = "uk_producto_universo_api",
           columnNames = {"universo", "api_id"}))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto")
public abstract class Producto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_id", nullable = false)
    private Integer apiId;

    @Column(nullable = false, length = 180)
    private String nombre;

    @Column(length = 500)
    private String imagen;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, length = 40)
    private String universo;

    protected Producto() {}

    public abstract String getDescripcionVenta();
    public abstract String getAtributoPrincipal();

    // Generar getters y setters en Eclipse.

    public String getNombre() { 
    	return nombre; 
    	}
    public void setNombre(String nombre) {
    	this.nombre = nombre; 
    	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getUniverso() {
		return universo;
	}

	public void setUniverso(String universo) {
		this.universo = universo;
	}

}
