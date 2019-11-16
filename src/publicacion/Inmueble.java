package publicacion;

import java.util.List;

import usuario.Usuario;

public class Inmueble {

	private String tipoInmueble;
	private String pais;
	private String ciudad;
	private Direccion direccion;
	private List<String> servicios;
	private Usuario propietario;
	
	public Inmueble(String tipoInmueble, String pais, String ciudad, Direccion direccion, List<String> servicios) {
		this.tipoInmueble = tipoInmueble;
		this.pais = pais;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.servicios = servicios;
	}

	public String getTipoInmueble() {
		return this.tipoInmueble;
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

	public Usuario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
		propietario.agregarInmueble(this);
	}

	public String obtenerNombrePropietario() {
		return this.propietario.getNombre();
	}
	
}
