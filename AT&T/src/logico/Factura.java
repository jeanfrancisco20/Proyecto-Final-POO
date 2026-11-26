package logico;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4959283773327261989L;
	//private static final long serialVersionUID = 1L;

	private String idFactura;
	private Cliente cliente;
	private Date fechaEmision;
	private Date fechaPago;
	private float montoTotal;
	private boolean pagada;
	public Factura(String idFactura, Cliente cliente, Date fechaEmision, Date fechaPago,
			float montoTotal, boolean pagada) {
		super();
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		this.montoTotal = montoTotal;
		this.pagada = pagada;
	}
	public String getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public boolean isPagada() {
		return pagada;
	}
	public void setPagada(boolean pagada) {
		this.pagada = pagada;
	}
}
