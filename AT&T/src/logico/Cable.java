package logico;

import java.sql.Date;

public class Cable extends Servicio {

	private int cantidadCanales;
	private String tipoPaqueteCable;
	
	public Cable(String idServicio, boolean estado, Date fechaContratacion, int cantidadCanales,
			String tipoPaqueteCable) {
		super(idServicio, estado, fechaContratacion);
		this.cantidadCanales = cantidadCanales;
		this.tipoPaqueteCable = tipoPaqueteCable;
	}

	public int getCantidadCanales() {
		return cantidadCanales;
	}

	public void setCantidadCanales(int cantidadCanales) {
		this.cantidadCanales = cantidadCanales;
	}

	public String getTipoPaqueteCable() {
		return tipoPaqueteCable;
	}

	public void setTipoPaqueteCable(String tipoPaqueteCable) {
		this.tipoPaqueteCable = tipoPaqueteCable;
	}

	@Override
	public float costoMensual() {
		float tarifaBase = 0;
	    
	    if ("Basico".equalsIgnoreCase(tipoPaqueteCable)) {
	        tarifaBase = 500;
	    } else if ("HD".equalsIgnoreCase(tipoPaqueteCable) || "Estandar".equalsIgnoreCase(tipoPaqueteCable)) {
	        tarifaBase = 900;
	    } else if ("Premium".equalsIgnoreCase(tipoPaqueteCable)) {
	        tarifaBase = 1400;
	    } else {
	        tarifaBase = 700;
	    }
	    
	    // Costo variable por la cantidad de canales ($3.50 por cada canal)
	    float costoCanales = cantidadCanales * 3.5f;
	    
	    return tarifaBase + costoCanales;
	}

}
