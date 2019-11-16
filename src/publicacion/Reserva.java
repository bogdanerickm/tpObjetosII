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
		this.getPublicacion().agregarReserva(this);
		this.getInquilino().agregarReserva(this);
		this.getPublicacion().getPropietario().notificarReservaPendiente(this);
	}

	public Publicacion getPublicacion() {
		return this.publicacion;
	}

	public Usuario getInquilino() {
		return this.inquilino;
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

	public String obtenerNombrePropietario() {
		return this.publicacion.obtenerNombrePropietario();
	}
	
}
