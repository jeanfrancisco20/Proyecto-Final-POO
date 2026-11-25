package logico;

import java.util.Date;

public class Administrativo extends Empleado {

	private static final long serialVersionUID = 1L;
	private float bonoAnual;

	public Administrativo(String idEmpleado, String nombre, String contrasena, String puesto,
			int horasTrabajadas, float pagoXHora, Date fechaIngreso, float bonoActual) {
		super(nombre, contrasena, idEmpleado, puesto, horasTrabajadas, pagoXHora, fechaIngreso);
		this.bonoAnual = bonoActual;
	}

	public float getBonoAnual() {
		return bonoAnual;
	}

	public void setBonoAnual(float bonoAnual) {
		this.bonoAnual = bonoAnual;
	}

	@Override
	public float calcularSalario() {
		return (horasTrabajadas * pagoXHora) + bonoAnual;
	}
}