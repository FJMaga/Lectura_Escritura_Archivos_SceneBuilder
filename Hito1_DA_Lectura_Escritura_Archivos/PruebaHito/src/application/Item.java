package application;

public class Item {
	private String date,nombre,ciudad,salario;

	
	public Item() {
		super();
	}

	public Item(String date, String nombre, String ciudad, String salario) {
		super();
		this.date = date;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.salario = salario;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Item [date=" + date + ", nombre=" + nombre + ", ciudad=" + ciudad + ", salario=" + salario + "]";
	}
	
	
}

