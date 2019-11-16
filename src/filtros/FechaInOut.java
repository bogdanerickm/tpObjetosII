package filtros;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import publicacion.Publicacion;

public class FechaInOut extends Filtro {

	private LocalDate checkIn;
	private LocalDate checkOut;
	
	public FechaInOut(LocalDate checkIn, LocalDate checkOut) {
		super();
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public List<Publicacion> filtrarPublicaciones(List<Publicacion> publicaciones) {
		List<Publicacion> copiaPublicaciones = publicaciones;
		copiaPublicaciones = copiaPublicaciones.stream().filter(publicacion -> ( (publicacion.estaDentroDeFecha(checkIn, checkOut)) && (publicacion.puedeReservarseEn(checkIn, checkOut)) )).collect(Collectors.toList());
		return copiaPublicaciones;
	}
}
