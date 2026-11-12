package logico;

public class Comercial extends Empleado {

	private int ventasRealizadas;
	private float comisionPorVenta;
	
	public Comercial(String idEmpleado, String nombre, String puesto, int horasTrabajadas, float pagoXHora,
			int ventasRealizadas, float comisionPorVenta) {
		super(idEmpleado, nombre, puesto, horasTrabajadas, pagoXHora);
		this.ventasRealizadas = ventasRealizadas;
		this.comisionPorVenta = comisionPorVenta;
	}

	public int getVentasRealizadas() {
		return ventasRealizadas;
	}

	public void setVentasRealizadas(int ventasRealizadas) {
		this.ventasRealizadas = ventasRealizadas;
	}

	public float getComisionPorVenta() {
		return comisionPorVenta;
	}

	public void setComisionPorVenta(float comisionPorVenta) {
		this.comisionPorVenta = comisionPorVenta;
	}

	@Override
	public float calcularSalario() {
		float pago= (horasTrabajadas*pagoXHora)+(ventasRealizadas*comisionPorVenta);
		return pago;
	}

}
