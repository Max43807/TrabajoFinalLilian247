/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analistas.ventas.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table(name = "ventas")
public class Venta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La fecha es requerida...")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
    private LocalDateTime fechaHora;
    
    @Column(name = "act", columnDefinition = "boolean default 1")
    private boolean activo;
    
    @Size(max = 65)
    private String descripcion;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "venta_id")
    public List<LineaVenta> lineas;
    
    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "fk_id_caja")
    private Caja cajas;
    
    //private Cliente cliente;
    //private Usuario cajero;

    public Venta() {
        lineas = new ArrayList<>();
        descripcion = "Ninguna";
        fechaHora = LocalDateTime.now();
        activo = true;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<LineaVenta> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaVenta> lineas) {
        this.lineas = lineas;
    }
    
    public void addLinea(LineaVenta linea) {
        lineas.add(linea);
    }
    
	public Caja getCajas() {
		return cajas;
	}

	public void setCajas(Caja cajas) {
		this.cajas = cajas;
	}

	public Double getTotal() {
        Double total = 0.0;
        
        for( LineaVenta ln : lineas) {
            total += ln.getSubtotal();
        }
        
        return total;
    }
}
