package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Sistema implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Cliente> misClientes;
	private static Sistema sistema= null;
	
	public Sistema() {
		super();
		this.misEmpleados = new ArrayList<Empleado>();
		this.misPlanes = new ArrayList<Plan>();
		this.misServicios = new ArrayList<Servicio>();
		this.misContratos = new ArrayList<Contrato>();
		this.misFacturas = new ArrayList<Factura>();
		this.misClientes = new ArrayList<Cliente>();
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
		if (factura != null && !factura.isPagada()) {
			factura.setPagada(true);
			factura.setFechaPago(new Date());
			
			if (factura.getCliente() != null) {
				factura.getCliente().facturasPorPagar();
			}
		}
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
	
	public void registrarEmpleado(Empleado empleado) {
		misEmpleados.add(empleado);
	}
	
	public void registrarContrato(Contrato contrato) {
		if (contrato == null || contrato.getCliente() == null) {
			return;
		}
		
		boolean existe = false;
	    boolean deuda= false;
	    
	    for (Contrato aux : misContratos) {
	        if (aux.getIdContrato().equals(contrato.getIdContrato())) {
	            existe = true;
	            break;
	        }
	    }
	    
	    Cliente cliente= contrato.getCliente();
	    cliente.facturasPorPagar();
	    deuda = cliente.isDesactivado();
	    
	    if (!existe && !deuda) {
	        misContratos.add(contrato);
	    }
	}
	
	private float calcularMontoCliente(Cliente cliente) {
		float total= 0.0f;
		
		for (Contrato contrato: misContratos) {
			if (contrato.getCliente()!= null && contrato.getCliente().getIdCliente().equalsIgnoreCase(cliente.getIdCliente())&&
					contrato.getPlan() != null) {
				total += contrato.getPlan().getPrecioBase();
			}
		}
		return total;
	}
	
	public ArrayList<Factura> generarFacturasTodosLosClientes(){
		ArrayList<Factura> generadas= new ArrayList<>();
		int numeroId= misFacturas.size() + 1;
		
		for (Cliente cliente: misClientes) {
			float monto= calcularMontoCliente(cliente);
			
			if(monto > 0) {
				String idFactura= "FAC-"+numeroId;
				numeroId++;
				
				Factura nuevaFactura= new Factura(idFactura, cliente, new Date(), null, monto, false);
				
				misFacturas.add(nuevaFactura);
				generadas.add(nuevaFactura);
				
				if (cliente.getFacturas() != null) {
					cliente.getFacturas().add(nuevaFactura);
					cliente.facturasPorPagar();
				}
			}
		}
		return generadas;
	}
	
	public void eliminarContrato(String id) {
		Contrato aux = buscarContratoPorId(id);
		if (aux != null && !aux.getEstadoContrato()) {
			misContratos.remove(aux);
		}
	}
}
