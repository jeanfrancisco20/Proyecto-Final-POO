package logico;

public class Trabajador extends Empleado {

	public Trabajador(String idEmpleado, String nombre, String puesto, int horasTrabajadas, float pagoXHora) {
		super(idEmpleado, nombre, puesto, horasTrabajadas, pagoXHora);
	}

	@Override
	public float calcularSalario() {
		float pago= horasTrabajadas*pagoXHora;
		return pago;
	}

}
