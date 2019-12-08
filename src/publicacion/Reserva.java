package publicacion;

import java.time.LocalDate;

import usuario.Usuario;

public class Reserva {
	private Publicacion publicacion;
	private Usuario inquilino;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private Boolean estaAceptada;
	
	public Reserva(Publicacion publicacion, Usuario inquilino, LocalDate checkIn, LocalDate checkOut) {
		this.estaAceptada = false;
		this.publicacion = publicacion;
		this.inquilino = inquilino;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Publicacion getPublicacion() {
		return this.publicacion;
	}
	
	public Usuario getPropietario() {
		return this.publicacion.getPropietario();
	}
	
	public String getNombrePropietario() {
		return this.publicacion.getNombrePropietario();
	}
	
	public Usuario getInquilino() {
		return this.inquilino;
	}
	
	public String getNombreInquilino() {
		return this.inquilino.getNombre();
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}

	public LocalDate getCheckOut() {
		return checkOut;
	}
	
	public Boolean estaAceptada() {
		return this.estaAceptada;
	}

	public void aceptar() {
		this.estaAceptada = true;
		this.getInquilino().notificarReservaConcretada(this);
	}
	
	public void notificarNuevaReserva() {
		this.getPropietario().notificarReservaPendiente(this);
	}
}
