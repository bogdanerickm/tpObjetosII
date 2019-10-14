package sitio;

import java.util.ArrayList;
import java.util.List;

import publicacion.Publicacion;
import publicacion.Servicio;

public class Sitio {

	private List<Publicacion> publicaciones;
	private List<Servicio> servicios;
	private ServidorMail servidorMail;
    private static Sitio sitio;
    public AdministradorDeSitio administradorDeSitio;
    
	public Sitio(ServidorMail servidor) {
		this.servidorMail = servidor;
	}
	
	private Sitio() {
		super();
		this.publicaciones = new ArrayList<Publicacion>();
		this.administradorDeSitio = new AdministradorDeSitio(this);
		this.servicios = new ArrayList<Servicio>();
	}
	
	public static Sitio getInstance() {
        if (sitio == null){
            sitio = new Sitio();
        }
        
        return sitio;
	}
	
	public ServidorMail getServidorMail() {
		return this.servidorMail;
	}
	
	public List<Publicacion> getPublicaciones() {
		return this.publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	public void agregarPublicacion(Publicacion publicacion) {
		this.publicaciones.add(publicacion);
	}


	public void eliminarPublicacion(Publicacion publicacion) {
		this.publicaciones.remove(publicacion);
	}


	public List<Servicio> getServicios() {
		return this.servicios;
	}


	public void agregarServicio(Servicio servicioNuevo) {
		this.servicios.add(servicioNuevo);
	}

}