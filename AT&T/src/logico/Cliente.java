package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente extends Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7752343539888906855L;
	//private static final long serialVersionUID = 1L;

	private String idCliente;
	private String telefono;
	private String  direccion;
	private boolean desactivado;
	private ArrayList<Contrato> contratos;
	private ArrayList<Factura> facturas;
	
	public Cliente(String nombre, String contrasena, String idCliente, String telefono, String direccion, boolean desactivado) {
		super(nombre, contrasena);
		this.idCliente = idCliente;
		this.telefono = telefono;
		this.direccion = direccion;
		this.desactivado = desactivado;
		this.contratos = new ArrayList<Contrato>();
		this.facturas = new ArrayList<Factura>();
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(ArrayList<Contrato> contratos) {
		this.contratos = contratos;
	}
	public ArrayList<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(ArrayList<Factura> facturas) {
		this.facturas = facturas;
	}
	public boolean isDesactivado() {
		return desactivado;
	}
	public void setDesactivado(boolean desactivado) {
		this.desactivado = desactivado;
	}
	public int facturasPorPagar() {
		int cont= 0;
		
		for(int i=0; i<facturas.size(); i++) {
			if(!facturas.get(i).isPagada()) {
				cont++;
			}
		}
		if (cont>2) {
			desactivado= true;
		}
		else {
			desactivado= false;
		}
		return cont;
	}
	
}
