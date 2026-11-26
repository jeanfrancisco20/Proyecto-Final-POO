package logico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Sistema implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<Empleado> misEmpleados;
	private ArrayList<Plan> misPlanes;
	private ArrayList<Servicio> misServicios;
	private ArrayList<Contrato> misContratos;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Cliente> misClientes;
	private static Sistema sistema = null;

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
		if (sistema == null) {
			sistema = new Sistema();
		}
		return sistema;
	}

	public static Sistema getSistema() {
		return sistema;
	}

	public static void setSistema(Sistema sistema) {
		Sistema.sistema = sistema;
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
		if (id == null) {
			return null;
		}
		for (Cliente c : misClientes) {
			if (c != null && c.getIdCliente() != null && id.equalsIgnoreCase(c.getIdCliente())) {
				return c;
			}
		}
		return null;
	}

	public Plan buscarPlanPorId(String id) {
		if (id == null) {
			return null;
		}
		for (Plan p : misPlanes) {
			if (p != null && p.getIdPlan() != null && id.equalsIgnoreCase(p.getIdPlan())) {
				return p;
			}
		}
		return null;
	}

	public Contrato buscarContratoPorId(String id) {
		if (id == null) {
			return null;
		}
		for (Contrato c : misContratos) {
			if (c != null && c.getIdContrato() != null && id.equalsIgnoreCase(c.getIdContrato())) {
				return c;
			}
		}
		return null;
	}

	public Factura buscarFacturaPorId(String id) {
		if (id == null) {
			return null;
		}
		for (Factura f : misFacturas) {
			if (f != null && f.getIdFactura() != null && id.equalsIgnoreCase(f.getIdFactura())) {
				return f;
			}
		}
		return null;
	}

	public void registrarPagoFactura(String idfactura) {
		Factura factura = buscarFacturaPorId(idfactura);
		if (factura != null && !factura.isPagada()) {
			factura.setPagada(true);
			factura.setFechaPago(new Date());

			if (factura.getCliente() != null) {
				factura.getCliente().facturasPorPagar();
			}
		}
	}

	public ArrayList<Factura> obtenerFacturasPendientes() {
		ArrayList<Factura> pendientes = new ArrayList<>();

		for (Factura f : misFacturas) {
			if (f != null && !f.isPagada()) {
				pendientes.add(f);
			}
		}
		return pendientes;
	}

	public void registrarCliente(Cliente cliente) {
		if (cliente == null || cliente.getIdCliente() == null) {
			return;
		}
		boolean existe = false;

		for (Cliente aux : misClientes) {
			if (aux != null && aux.getIdCliente() != null && aux.getIdCliente().equals(cliente.getIdCliente())) {
				existe = true;
				break;
			}
		}

		if (!existe) {
			misClientes.add(cliente);
		}
	}

	public void registrarPlan(Plan plan) {
		if (plan == null || plan.getIdPlan() == null) {
			return;
		}
		boolean existe = false;

		for (Plan aux : misPlanes) {
			if (aux != null && aux.getIdPlan() != null && aux.getIdPlan().equals(plan.getIdPlan())) {
				existe = true;
				break;
			}
		}

		if (!existe) {
			misPlanes.add(plan);
		}
	}

	public void registrarEmpleado(Empleado empleado) {
		if (empleado != null) {
			misEmpleados.add(empleado);
		}
	}

	public void registrarContrato(Contrato contrato) {
		if (contrato == null || contrato.getCliente() == null) {
			return;
		}

		boolean existe = false;
		boolean deuda = false;

		for (Contrato aux : misContratos) {
			if (aux != null && aux.getIdContrato() != null && contrato.getIdContrato() != null
					&& aux.getIdContrato().equals(contrato.getIdContrato())) {
				existe = true;
				break;
			}
		}

		Cliente cliente = contrato.getCliente();
		cliente.facturasPorPagar();
		deuda = cliente.isDesactivado();

		if (!existe && !deuda) {
			misContratos.add(contrato);
		}

		if (contrato.getComercialAsignado() instanceof Comercial) {
			Comercial comercial = (Comercial) contrato.getComercialAsignado();
			comercial.setVentasRealizadas(comercial.getVentasRealizadas() + 1);
		}
	}

	private float calcularMontoCliente(Cliente cliente) {
		float total = 0.0f;
		if (cliente == null || cliente.getIdCliente() == null) {
			return total;
		}

		for (Contrato contrato : misContratos) {
			if (contrato != null && contrato.getCliente() != null && contrato.getCliente().getIdCliente() != null
					&& contrato.getCliente().getIdCliente().equalsIgnoreCase(cliente.getIdCliente())
					&& contrato.getPlan() != null) {
				total += contrato.getPlan().getPrecioBase();
			}
		}
		return total;
	}

	public ArrayList<Factura> generarFacturasTodosLosClientes() {
		ArrayList<Factura> generadas = new ArrayList<>();
		int numeroId = misFacturas.size() + 1;

		for (Cliente cliente : misClientes) {
			float monto = calcularMontoCliente(cliente);

			if (monto > 0 && !clienteYaFacturado(cliente)) {
				String idFactura = "FAC-" + numeroId;
				numeroId++;

				Factura nuevaFactura = new Factura(idFactura, cliente, new Date(), null, monto, false);

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

	private boolean clienteYaFacturado(Cliente cliente) {
		if (cliente == null || cliente.getIdCliente() == null) {
			return false;
		}
		for (Factura f : misFacturas) {
			if (f != null && f.getCliente() != null && f.getCliente().getIdCliente() != null
					&& f.getCliente().getIdCliente().equalsIgnoreCase(cliente.getIdCliente())) {
				return true;
			}
		}
		return false;
	}

	public void eliminarContrato(String id) {
		Contrato aux = buscarContratoPorId(id);
		if (aux != null && !aux.getEstadoContrato()) {
			misContratos.remove(aux);
		}
	}

	public boolean confirmarLogin(String usuario, String contrasena) {
		if (usuario == null || contrasena == null) {
			return false;
		}

		if (misEmpleados != null) {
			for (Empleado emp : misEmpleados) {
				if (emp != null && usuario.equals(emp.getNombre()) && contrasena.equals(emp.getContrasena())) {
					return true;
				}
			}
		}

		if (misClientes != null) {
			for (Cliente cli : misClientes) {
				if (cli != null && usuario.equals(cli.getNombre()) && contrasena.equals(cli.getContrasena())) {
					return true;
				}
			}
		}

		return false;
	}
}
