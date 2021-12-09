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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

/**
 *
 * @author jorge
 */
@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id_com")
    private Long id;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
    @Column(name = "fec_hor")
    private LocalDateTime fechaHora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_usu")
    private User user;

    @Size(max = 70)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_ik_provee")
    private Proveedor proveedor;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "fk_id_ven")
    private List<LineaCompra> lineas;

    public Compra() {
        lineas = new ArrayList<>();
        descripcion = "Ninguna";
        fechaHora = LocalDateTime.now();
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

    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<LineaCompra> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaCompra> lineas) {
        this.lineas = lineas;
    }

    public void addLinea(LineaCompra linea) {
        lineas.add(linea);
    }

    public Double getTotal() {

        Double total = 0.0;
        for (LineaCompra ln : lineas) {
            total -= ln.getSubtotal();
        }
        return total;
    }
}
