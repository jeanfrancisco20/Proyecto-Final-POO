package logico;

import java.util.ArrayList;
import java.util.Date;

public class Contrato {

	private String idContrato;
	private Cliente cliente;
	private Plan plan;
	private Comercial comercialAsignado;
	private Date fechaInicio;
	private boolean estadoContrato;
	private float cargoFijoMensual;
	private ArrayList<Factura> facturas;
	public Contrato(String idContrato, Cliente cliente, Plan plan, Comercial comercialAsignado, Date fechaInicio,
			boolean estadoContrato, float cargoFijoMensual) {
		super();
		this.idContrato = idContrato;
		this.cliente = cliente;
		this.plan = plan;
		this.comercialAsignado = comercialAsignado;
		this.fechaInicio = fechaInicio;
		this.estadoContrato = estadoContrato;
		this.cargoFijoMensual = cargoFijoMensual;
		this.facturas= new ArrayList<Factura>();
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
	public boolean getEstadoContrato() {
		return estadoContrato;
	}
	public void setEstadoContrato(boolean estadoContrato) {
		this.estadoContrato = estadoContrato;
	}
	public float getCargoFijoMensual() {
		return cargoFijoMensual;
	}
	public void setCargoFijoMensual(float cargoFijoMensual) {
		this.cargoFijoMensual = cargoFijoMensual;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
}
