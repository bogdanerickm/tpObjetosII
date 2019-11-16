package sitio;

import java.util.ArrayList;
import java.util.List;

import filtros.Filtro;
import publicacion.Publicacion;
import usuario.Usuario;

public class Sitio {

	private List<Usuario> usuarios;
	private List<Publicacion> publicaciones;
	private List<String> servicios;
	private List<String> tiposInmueble;
	private ServidorMail servidorMail;
 	
	public Sitio() {
		super();
		this.usuarios = new ArrayList<Usuario>();
		this.publicaciones = new ArrayList<Publicacion>();
		this.servicios = new ArrayList<String>();
		this.tiposInmueble = new ArrayList<String>();
	}

	public Sitio(ServidorMail servidor) {
		this();
		this.servidorMail = servidor;
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
	
	public void registrarUsuario(Usuario usuario) {
		this.usuarios.add(usuario);
	}
	
	public List<Publicacion> getPublicaciones() {
		return this.publicaciones;
	}
	
	public void agregarPublicacion(Publicacion publicacion) {
		this.publicaciones.add(publicacion);
	}
	
	public void eliminarPublicacion(Publicacion publicacion) {
		this.publicaciones.remove(publicacion);
	}
	
	public void vaciarListaPublicaciones() {
		this.publicaciones.clear();
	}
	
	public ServidorMail getServidorMail() {
		return this.servidorMail;
	}	

	public List<String> getServicios() {
		return this.servicios;
	}

	public void agregarServicio(String servicioNuevo) {
		this.servicios.add(servicioNuevo);
	}
	public List<String> getTiposInmueble() {
		return this.tiposInmueble;
	}

	public void agregarTipoInmueble(String tipoInmueble) {
		this.tiposInmueble.add(tipoInmueble);
	}
	
	public List<Publicacion> buscarPublicaciones(Filtro filtro) {
		return filtro.filtrarPublicaciones(this.publicaciones);
	}

	public void enviarMail(String email, String asunto, String texto) {
		this.servidorMail.enviarMail(email, asunto, texto);
	}
}
