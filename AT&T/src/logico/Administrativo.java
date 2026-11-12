package logico;

public class Administrativo extends Empleado {

	private float bonoAnual;
	
	public Administrativo(String idEmpleado, String nombre, String puesto, int horasTrabajadas, float pagoXHora,
			float bonoAnual) {
		super(idEmpleado, nombre, puesto, horasTrabajadas, pagoXHora);
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
