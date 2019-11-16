package filtros;

import java.util.List;

import publicacion.Publicacion;

public class FiltroAND extends Filtro{

	private Filtro filtro1;
	private Filtro filtro2;
	
	public FiltroAND(Filtro filtro1, Filtro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}

	@Override
	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		List<Publicacion> copiaPublicaciones = publicaciones;
		copiaPublicaciones = filtro1.filtrarPublicaciones(copiaPublicaciones);
		copiaPublicaciones = filtro2.filtrarPublicaciones(copiaPublicaciones);
		
		return copiaPublicaciones;
	}
	
}