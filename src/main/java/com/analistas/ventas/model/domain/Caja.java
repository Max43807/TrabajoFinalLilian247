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
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
@Table(name = "cajas")
public class Caja implements Serializable {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "fk_id_usu")
	 private User user;
	 
	 @NotNull(message = "El Monto es requerido ponerlo...")
	 @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
	 private Double montoInicial;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
	 @Column(name = "fec_hor_aper")
	 private LocalDateTime fecha_Hora_apertura;
	 
	 @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
	 private Double montoFinal;
	 
	 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm z")
	    @Column(name = "_fec_hor_cie")
	    private LocalDateTime fecha_Hora_cierre;
	 
	 @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
	    private Double ingreso;
	 
	 @NumberFormat(pattern = "#,##0.00", style = NumberFormat.Style.CURRENCY)
	    private Double cajaTotal;
	 
	 @Column(name = "total_ventas")
	 private int total_ventas;
	 
	 @Column(name = "act", columnDefinition = "boolean default 1")
	 private boolean activo;
	  
	 @OneToMany(fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL,
	            orphanRemoval = true)
	    @JoinColumn(name = "fk_id_venta")
	    public List<Venta> ventas;
	 
	 @OneToMany(fetch = FetchType.LAZY,
	            cascade = CascadeType.ALL,
	            orphanRemoval = true)
	    @JoinColumn(name = "fk_id_CAJACOM")
	    public List<Compra> compras;

	 public Caja() {
	        ventas = new ArrayList<>();
	        compras = new ArrayList<>();
	        activo = true;
	        cajaTotal = 0.00;
	    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUsuario() {
		return user;
	}

	public void setUsuario(User user) {
		this.user = user;
	}

	public Double getMontoInicial() {
		return montoInicial;
	}

	public void setMontoInicial(Double montoInicial) {
		this.montoInicial = montoInicial;
	}

	public LocalDateTime getFecha_Hora_apertura() {
		return fecha_Hora_apertura;
	}

	public void setFecha_Hora_apertura(LocalDateTime fecha_Hora_apertura) {
		this.fecha_Hora_apertura = fecha_Hora_apertura;
	}

	public Double getMontoFinal() {
		return montoFinal;
	}

	public void setMontoFinal(Double montoFinal) {
		this.montoFinal = montoFinal;
	}

	public LocalDateTime getFecha_Hora_cierre() {
		return fecha_Hora_cierre;
	}

	public void setFecha_Hora_cierre(LocalDateTime fecha_Hora_cierre) {
		this.fecha_Hora_cierre = fecha_Hora_cierre;
	}

	public Double getIngreso() {
		return ingreso;
	}

	public void setIngreso(Double ingreso) {
		this.ingreso = ingreso;
	}

	public Double getCaja_total() {
		return cajaTotal;
	}

	public void setCaja_total(Double cajaTotal) {
		this.cajaTotal = cajaTotal;
	}

	public int getTotal_ventas() {
		return total_ventas;
	}

	public void setTotal_ventas(int total_ventas) {
		this.total_ventas = total_ventas;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Double getCajaTotal() {
		return cajaTotal;
	}

	public void setCajaTotal(Double cajaTotal) {
		this.cajaTotal = cajaTotal;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}
	
	public Double getTotalVentasCaja() {
        Double total = 0.0;
            for (Venta v : ventas) {
                total += v.getTotal();
                
            }      
        return total;
    }
	
	public Double getTotalComprasCaja() {
        Double total = 0.0;
            for (Compra c : compras) {
                total -= c.getTotal();
                
            }      
        return total;
    }
	 
}
