package filtros;

import java.util.ArrayList;
import java.util.List;

import publicacion.Publicacion;

public class FiltroCompuesto extends Filtro {
	
	private List<Filtro> filtros;

	public FiltroCompuesto() {
		super();
		this.filtros = new ArrayList<Filtro>();
	}
	
	@Override
	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		List<Publicacion> copiaPublicaciones = publicaciones;
		for (Filtro unFiltro: filtros) {
			copiaPublicaciones = unFiltro.filtrarPublicaciones(copiaPublicaciones);
		}
		return copiaPublicaciones;
	}
	
	public void agregarFiltro(Filtro filtro) {
		this.filtros.add(filtro);
	}

}
