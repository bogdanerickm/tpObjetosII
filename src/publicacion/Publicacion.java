package publicacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import usuario.Usuario;

public class Publicacion {

	private Inmueble inmueble;
	private int capacidad;
	private LocalDate checkIn;
	private LocalDate checkOut;
	private double precio;
	private List<Reserva> reservas;
	
	public Publicacion(Inmueble inmueble, int capacidad, LocalDate checkIn, LocalDate checkOut, double precio) {
		this.inmueble = inmueble;
		this.capacidad = capacidad;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.precio = precio;
		this.reservas = new ArrayList<Reserva>();
	}

	public Inmueble getInmueble() {
		return this.inmueble;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public double getPrecio() {
		return this.precio;
	}

	public LocalDate getCheckIn() {
		return checkIn;
	}
	
	public LocalDate getCheckOut() {
		return checkOut;
	}		

	public Reserva agregarReserva(Reserva reserva) {
		this.reservas.add(reserva);
		return reserva;
	}
	
	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public Usuario getPropietario() {
		return this.getInmueble().getPropietario();
	}
	
	public String getNombrePropietario() {
		return this.inmueble.getNombrePropietario();
	}

	public Boolean puedeReservarseEn(LocalDate checkIn, LocalDate checkOut) {
		if(tieneReservas()) {
			List<Boolean> collect = this.getReservas().stream().filter(reserva -> reserva.estaAceptada()).map(reserva -> this.tieneReservasPara(checkIn, checkOut, reserva)).collect(Collectors.toList());
			return (!collect.contains(true));
		} else {
			return true;
		}
	}

	private boolean tieneReservas() {
		return this.getReservas().size() > 0;
	}

	private Boolean tieneReservasPara(LocalDate checkIn, LocalDate checkOut, Reserva reserva) {
		return (checkIn.isBefore(reserva.getCheckOut()) && reserva.getCheckIn().isBefore(checkOut));
	}

	public Boolean estaDentroDeFecha(LocalDate checkIn, LocalDate checkOut) {
		return (this.getCheckIn().isBefore(checkIn) || this.getCheckIn().isEqual(checkIn)) && (this.getCheckOut().isAfter(checkOut) || this.getCheckOut().isEqual(checkOut));
	}

}
