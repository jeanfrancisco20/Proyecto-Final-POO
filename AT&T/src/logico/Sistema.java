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
	private static Sistema sistema= null;
	
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
	public static Sistema getInstance() {
		if (sistema==null) {
			sistema= new Sistema();
		}
		return sistema;
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
	public Cliente buscarClientePorId(String id) {
		Cliente cliente= null;
		for (Cliente c: misClientes) {
			if (id.equalsIgnoreCase(c.getIdCliente())) {
				cliente= c;
				break;
			}
		}
		return cliente;
	}
	public Plan buscarPlanPorId(String id) {
		Plan plan= null;
		for (Plan p: misPlanes) {
			if (id.equalsIgnoreCase(p.getIdPlan())) {
				plan= p;
				break;
			}
		}
		return plan;
	}
	public Contrato buscarContratoPorId(String id) {
		Contrato contrato= null;
		for (Contrato c: misContratos) {
			if (id.equalsIgnoreCase(c.getIdContrato())) {
				contrato= c;
				break;
			}
		}
		return contrato;
	}
	public Factura buscarFacturaPorId(String id) {
		Factura factura= null;
		for (Factura f: misFacturas) {
			if (id.equalsIgnoreCase(f.getIdFactura())) {
				factura= f;
				break;
			}
		}
		return factura;
	}
	public void registrarPagoFactura(String idfactura) {
		Factura factura= buscarFacturaPorId(idfactura);
		factura.setPagada(true);
	}
	public ArrayList<Factura> obtenerFacturasPendientes(){
		ArrayList<Factura> pendientes= new ArrayList<>();
		
		for (Factura f: misFacturas) {
			if(!f.isPagada()) {
				pendientes.add(f);
			}
		}
		return pendientes;
	}
	public void registrarCliente(Cliente cliente) {
	    boolean existe = false;
	    
	    for (Cliente aux : misClientes) {
	        if (aux.getIdCliente().equals(cliente.getIdCliente())) {
	            existe = true;
	            break;
	        }
	    }
	    
	    if (!existe) {
	        misClientes.add(cliente);
	    }
	}
	public void registrarPlan(Plan plan) {
	    boolean existe = false;
	    
	    for (Plan aux : misPlanes) {
	        if (aux.getIdPlan().equals(plan.getIdPlan())) {
	            existe = true;
	            break;
	        }
	    }
	    
	    if (!existe) {
	        misPlanes.add(plan);
	    }
	}
	
	public void registrarContrato(Contrato c) {
	    boolean existe = false;
	    
	    for (Contrato aux : misContratos) {
	        if (aux.getIdContrato().equals(c.getIdContrato())) {
	            existe = true;
	            break;
	        }
	    }
	    
	    if (!existe) {
	        misContratos.add(c);
	    }
	}
}
