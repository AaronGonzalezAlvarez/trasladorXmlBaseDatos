package trasladorXmlBaseDatos;

public class Curso {
	
	public String titulo;
	public String fecha;
	public String universidad;
	public Profesor profesor;
	
	public Curso(String titulo,String fecha, String universidad,Profesor profesor) {
		this.titulo = titulo;
		this.fecha = fecha;	
		this.universidad = universidad;
		this.profesor = profesor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	
	

}
