package logico;

import java.util.ArrayList;
import java.util.Date;

public class Reporte {

	private String idReporte;
	private String tipoReporte;
	private Date fechaInicio;
	private Date fechaFin;
	private String filtro;
	private ArrayList<Object> datosResultadObjects;
	public Reporte(String idReporte, String tipoReporte, Date fechaInicio, Date fechaFin, String filtro,
			ArrayList<Object> datosResultadObjects) {
		super();
		this.idReporte = idReporte;
		this.tipoReporte = tipoReporte;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.filtro = filtro;
		this.datosResultadObjects = datosResultadObjects;
	}
	public String getIdReporte() {
		return idReporte;
	}
	public void setIdReporte(String idReporte) {
		this.idReporte = idReporte;
	}
	public String getTipoReporte() {
		return tipoReporte;
	}
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public ArrayList<Object> getDatosResultadObjects() {
		return datosResultadObjects;
	}
	public void setDatosResultadObjects(ArrayList<Object> datosResultadObjects) {
		this.datosResultadObjects = datosResultadObjects;
	}
	
	
}
