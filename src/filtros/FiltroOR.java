package filtros;

import java.util.List;

import publicacion.Publicacion;

public class FiltroOR extends Filtro{

	private Filtro filtro1;
	private Filtro filtro2;
	
	public FiltroOR(Filtro filtro1, Filtro filtro2) {
		this.filtro1 = filtro1;
		this.filtro2 = filtro2;
	}

	@Override
	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		List<Publicacion> lista1 = publicaciones;
		List<Publicacion> lista2 = publicaciones;
		
		lista1 = filtro1.filtrarPublicaciones(lista1);
		lista2 = filtro2.filtrarPublicaciones(lista2);
		
		lista1.removeAll(lista2);
		lista2.addAll(lista1);
		return lista2;
	}
	
}
