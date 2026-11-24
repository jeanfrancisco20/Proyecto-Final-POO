package logico;

import java.util.Date;

public class Administrativo extends Empleado {

	private float bonoAnual;
	
	public Administrativo(String idEmpleado, String contrasena, String nombre, String puesto, int horasTrabajadas, float pagoXHora,
			float bonoAnual, Date fechaIngreso) {
		super(idEmpleado, nombre, contrasena, puesto, horasTrabajadas, pagoXHora, fechaIngreso);
		this.bonoAnual = bonoAnual;
	}

	public float getBonoAnual() {
		return bonoAnual;
	}

	public void setBonoAnual(float bonoAnual) {
		this.bonoAnual = bonoAnual;
	}

	@Override
	public float calcularSalario() {
		float pago= (horasTrabajadas*pagoXHora)+bonoAnual;
		return pago;
	}

}
