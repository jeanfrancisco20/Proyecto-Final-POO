package logico;

import java.sql.Date;

public class Telefono extends Servicio {

	private String numeroTelefono;
	private String tipoLinea;
	private int minutosIncluidos;
	
	public Telefono(String idServicio, boolean estado, Date fechaContratacion, String numeroTelefono, String tipoLinea,
			int minutosIncluidos) {
		super(idServicio, estado, fechaContratacion);
		this.numeroTelefono = numeroTelefono;
		this.tipoLinea = tipoLinea;
		this.minutosIncluidos = minutosIncluidos;
	}


	public String getNumeroTelefono() {
		return numeroTelefono;
	}


	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}


	public String getTipoLinea() {
		return tipoLinea;
	}


	public void setTipoLinea(String tipoLinea) {
		this.tipoLinea = tipoLinea;
	}


	public int getMinutosIncluidos() {
		return minutosIncluidos;
	}


	public void setMinutosIncluidos(int minutosIncluidos) {
		this.minutosIncluidos = minutosIncluidos;
	}


	@Override
	public float costoMensual() {
		float tarifaBase = 0;
	    
	    if ("Movil".equalsIgnoreCase(tipoLinea)) {
	        tarifaBase = 500;
	    } else if ("Fijo".equalsIgnoreCase(tipoLinea)) {
	        tarifaBase = 350;
	    } else if ("VoIP".equalsIgnoreCase(tipoLinea)) {
	        tarifaBase = 250;
	    } else {
	        tarifaBase = 300;
	    }
	    
	    // Costo por minutos (si es -1 representa minutos ilimitados con tarifa plana)
	    float costoMinutos;
	    if (minutosIncluidos == -1) {
	        costoMinutos = 600.0f; 
	    } else {
	        costoMinutos = minutosIncluidos * 1.5f;
	    }
	    
	    return tarifaBase + costoMinutos;
	}

}
