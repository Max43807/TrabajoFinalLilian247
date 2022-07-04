package com.analistas.ventas.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
	 private User usuario;
	 
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
	    private Double caja_total;
	 
	 @Column(name = "total_ventas")
	 private int total_ventas;
	 
	 @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(name = "fk_id_ven") //CLAVE FORANEA     
	    private Venta venta;
	 
	 @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	    @JoinColumn(name = "fk_id_com") //CLAVE FORANEA     
	    private Compra compra;
}
