package filtros;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import publicacion.Publicacion;

public class CheckOut extends Filtro {

	private LocalDate checkOut;
	
	public CheckOut(LocalDate checkOut) {
		super();
		this.checkOut = checkOut;
	}

	@Override
	public List<Publicacion> filtrarPublicaciones() {
		return this.copiaPublicaciones.stream().filter(publicacion -> publicacion.getCheckOut().equals(this.checkOut)).collect(Collectors.toList());
	}

}