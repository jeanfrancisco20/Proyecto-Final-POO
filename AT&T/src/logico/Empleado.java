package logico;

import java.io.Serializable;
import java.util.Date;

public abstract class Empleado extends Persona implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1877579970217624978L;
	//private static final long serialVersionUID = 1L;

	protected String idEmpleado;
	protected String puesto;
	protected int horasTrabajadas;
	protected float pagoXHora;
	protected Date fechaIngreso;
	
	public Empleado(String nombre, String contrasena, String idEmpleado, String puesto, int horasTrabajadas, float pagoXHora, Date fechaIngreso) {
		super(nombre, contrasena);
		this.idEmpleado = idEmpleado;
		this.puesto = puesto;
		this.horasTrabajadas = horasTrabajadas;
		this.pagoXHora = pagoXHora;
		this.fechaIngreso = fechaIngreso;
	}

	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}

	public void setHorasTrabajadas(int horasTrabajadas) {
		this.horasTrabajadas = horasTrabajadas;
	}

	public float getPagoXHora() {
		return pagoXHora;
	}

	public void setPagoXHora(float pagoXHora) {
		this.pagoXHora = pagoXHora;
	}
	
	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public abstract float calcularSalario();
	
}
