package logico;

public class Persona {
	protected String nombre;
	protected String contrasena;
	
	public Persona(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
