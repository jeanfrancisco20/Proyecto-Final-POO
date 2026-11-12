package logico;

import java.sql.Date;

public class Internet extends Servicio {

	private int velocidadBajada;
	private int velocidadSubida;
	private String tipoConexion;
	private float limiteDatosGB;
	
	public Internet(String idServicio, boolean estado, Date fechaContratacion, int velocidadBajada, int velocidadSubida,
			String tipoConexion, float limiteDatosGB) {
		super(idServicio, estado, fechaContratacion);
		this.velocidadBajada = velocidadBajada;
		this.velocidadSubida = velocidadSubida;
		this.tipoConexion = tipoConexion;
		this.limiteDatosGB = limiteDatosGB;
	}


	public int getVelocidadBajada() {
		return velocidadBajada;
	}


	public void setVelocidadBajada(int velocidadBajada) {
		this.velocidadBajada = velocidadBajada;
	}


	public int getVelocidadSubida() {
		return velocidadSubida;
	}


	public void setVelocidadSubida(int velocidadSubida) {
		this.velocidadSubida = velocidadSubida;
	}


	public String getTipoConexion() {
		return tipoConexion;
	}


	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}


	public float getLimiteDatosGB() {
		return limiteDatosGB;
	}


	public void setLimiteDatosGB(float limiteDatosGB) {
		this.limiteDatosGB = limiteDatosGB;
	}


	@Override
	public float costoMensual() {
		float tarifaBase = 0;

		if ("Fibra Optica".equalsIgnoreCase(tipoConexion)) {
	        tarifaBase = 800;
	    } else if ("HFC".equalsIgnoreCase(tipoConexion)) {
	        tarifaBase = 600;
	    } else if ("ADSL".equalsIgnoreCase(tipoConexion) || "Inalambrico".equalsIgnoreCase(tipoConexion)) {
	        tarifaBase = 400;
	    } else {
	        tarifaBase = 500;
	    }
	    
	    // Costo variable por velocidad ($8 por Mbps de bajada y $4 por Mbps de subida)
	    float costoVelocidad = (velocidadBajada * 8.0f) + (velocidadSubida * 4.0f);
	    
	    // Ajuste si es ilimitado (<= 0) o un paquete masivo de datos (> 500 GB)
	    float costoDatos = (limiteDatosGB <= 0 || limiteDatosGB > 500) ? 300.0f : 0.0f;
	    
	    return tarifaBase + costoVelocidad + costoDatos;	
	}

}
