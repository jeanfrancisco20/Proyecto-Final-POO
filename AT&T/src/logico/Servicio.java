package logico;

import java.sql.Date;

public abstract class Servicio {
	protected String idServicio;
	protected boolean estado;
	protected Date fechaContratacion;
	
	public Servicio(String idServicio, boolean estado, Date fechaContratacion) {
		super();
		this.idServicio = idServicio;
		this.estado = estado;
		this.fechaContratacion = fechaContratacion;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public abstract float costoMensual();
	
}
