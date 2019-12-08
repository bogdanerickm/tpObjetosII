package publicacion;

import java.util.List;

import usuario.Usuario;

public class Inmueble {

	private String pais;
	private String ciudad;
	private Direccion direccion;
	private List<String> servicios;
	private String tipoInmueble;
	private Usuario propietario;
	
	public Inmueble(String tipoInmueble, String pais, String ciudad, Direccion direccion, List<String> servicios, Usuario propietario) {
		this.tipoInmueble = tipoInmueble;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
		this.propietario = propietario;
	}
	
	public String getPais() {
		return this.pais;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}

	public List<String> getServicios() {
		return this.servicios;
	}
	
	public String getTipoInmueble() {
		return this.tipoInmueble;
	}

	public Usuario getPropietario() {
		return this.propietario;
	}

	public String getNombrePropietario() {
		return this.propietario.getNombre();
	}
}
