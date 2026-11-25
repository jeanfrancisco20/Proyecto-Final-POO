package logico;

import java.util.Date;

public class Comercial extends Empleado {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ventasRealizadas;
	private float comisionPorVenta;
	
	public Comercial(String idEmpleado, String nombre, String contrasena, String puesto, int horasTrabajadas, float pagoXHora,
			Date fechaIngreso, int ventasRealizadas, float comisionPorVenta) {
		super(idEmpleado, nombre, contrasena, puesto, horasTrabajadas, pagoXHora, fechaIngreso);
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
