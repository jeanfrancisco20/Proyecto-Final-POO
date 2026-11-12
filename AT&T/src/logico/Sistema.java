package logico;

import java.util.ArrayList;

public class Sistema {

	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Cliente> misClientes;
	private ArrayList<Reporte> misReportes;
	public Sistema() {
		super();
		this.misEmpleados = new ArrayList<Empleado>();
		this.misPlanes = new ArrayList<Plan>();
		this.misServicios = new ArrayList<Servicio>();
		this.misContratos = new ArrayList<Contrato>();
		this.misFacturas = new ArrayList<Factura>();
		this.misClientes = new ArrayList<Cliente>();
		this.misReportes = new ArrayList<Reporte>();
	}
	public ArrayList<Empleado> getMisEmpleados() {
		return misEmpleados;
	}
	public void setMisEmpleados(ArrayList<Empleado> misEmpleados) {
		this.misEmpleados = misEmpleados;
	}
	public ArrayList<Plan> getMisPlanes() {
		return misPlanes;
	}
	public void setMisPlanes(ArrayList<Plan> misPlanes) {
		this.misPlanes = misPlanes;
	}
	public ArrayList<Servicio> getMisServicios() {
		return misServicios;
	}
	public void setMisServicios(ArrayList<Servicio> misServicios) {
		this.misServicios = misServicios;
	}
	public ArrayList<Contrato> getMisContratos() {
		return misContratos;
	}
	public void setMisContratos(ArrayList<Contrato> misContratos) {
		this.misContratos = misContratos;
	}
	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}
	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}
	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}
	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}
	public ArrayList<Reporte> getMisReportes() {
		return misReportes;
	}
	public void setMisReportes(ArrayList<Reporte> misReportes) {
		this.misReportes = misReportes;
	}
}
