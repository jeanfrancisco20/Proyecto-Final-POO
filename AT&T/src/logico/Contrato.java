package logico;

import java.util.Date;

public class Contrato {

	private String idContrato;
	private Cliente cliente;
	private Plan plan;
	private Comercial comercialAsignado;
	private Date fechaInicio;
	private String estadoContrato;
	private float cargoFijoMensual;
	public Contrato(String idContrato, Cliente cliente, Plan plan, Comercial comercialAsignado, Date fechaInicio,
			String estadoContrato, float cargoFijoMensual) {
		super();
		this.idContrato = idContrato;
		this.cliente = cliente;
		this.plan = plan;
		this.comercialAsignado = comercialAsignado;
		this.fechaInicio = fechaInicio;
		this.estadoContrato = estadoContrato;
		this.cargoFijoMensual = cargoFijoMensual;
	}
	public String getIdContrato() {
		return idContrato;
	}
	public void setIdContrato(String idContrato) {
		this.idContrato = idContrato;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Comercial getComercialAsignado() {
		return comercialAsignado;
	}
	public void setComercialAsignado(Comercial comercialAsignado) {
		this.comercialAsignado = comercialAsignado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(String estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	public float getCargoFijoMensual() {
		return cargoFijoMensual;
	}
	public void setCargoFijoMensual(float cargoFijoMensual) {
		this.cargoFijoMensual = cargoFijoMensual;
	}
	
	
}
