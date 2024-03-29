
package com.analistas.ventas.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "articulos")
public class Articulo implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
   
    
    @Column(name = "cod_barras")
    private int codBarras;
    
    @NotBlank(message = "El campo no debe estar vacio")
    @Size(max = 65)
    private String descripcion;
    
    @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
    private Double precio;
    
    
    private int stock;
    
    @Size(max = 255)
    private String linkImagen;
    
    @Column(name = "act", columnDefinition = "boolean default 1")
    private boolean activo;

    public Articulo() {
        activo = true;
   }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(int codBarras) {
        this.codBarras = codBarras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    
    public boolean sinStock() {
        return this.stock < 0;
    }

    public void restarStock(int stock) {
    	this.stock -= stock;
    }
    
    public void sumarStock(int stock) {
    	this.stock += stock;
    }
    
    
    public String getLinkImagen() {
        return linkImagen;
    }

    public void setLinkImagen(String linkImagen) {
        this.linkImagen = linkImagen;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return id + " - " + descripcion + " - " + precio;
    }
}
