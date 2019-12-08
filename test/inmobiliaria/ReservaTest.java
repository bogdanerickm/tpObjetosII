package inmobiliaria;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Publicacion;
import publicacion.Reserva;
import usuario.Usuario;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;

class ReservaTest {
	
	private Usuario propietario;
	private Reserva reserva;
	private Publicacion publicacion;
	private Usuario inquilino;
	private LocalDate checkIn;
	private LocalDate checkOut;
	
	@BeforeEach
	public void setUp() throws Exception {
		propietario = mock(Usuario.class);
		publicacion = mock(Publicacion.class);
		inquilino = mock(Usuario.class);
		checkIn = LocalDate.of(2019, Month.SEPTEMBER, 25);
		checkOut = LocalDate.of(2019, Month.SEPTEMBER, 29);
		when(publicacion.getPropietario()).thenReturn(propietario);
		reserva = new Reserva(publicacion, inquilino, checkIn, checkOut);
	}
	
	@Test
	void testConstructorReserva() {
		assertEquals(reserva.getPublicacion(), publicacion);
		assertEquals(reserva.getInquilino(), inquilino); 
		assertEquals(reserva.getNombreInquilino(), inquilino.getNombre());
		assertEquals(reserva.getCheckIn(), checkIn);
		assertEquals(reserva.getCheckOut(), checkOut);
		assertEquals(reserva.getNombrePropietario(), propietario.getNombre());
		assertFalse(reserva.estaAceptada());
	}
	
	@Test
	void testCuandoUnaReservaSeAceptaSeNotificaAlInquilino() {
		reserva.aceptar();
		assertTrue(reserva.estaAceptada());
		verify(inquilino, times(1)).notificarReservaConcretada(reserva);
	}
	
	@Test
	void testCuandoUnaReservaSeCreaSeAgregaALaListaDeReservasDeLaPublicacionCorrespondiente() {
		Reserva nuevaReserva = new Reserva(publicacion, inquilino, checkIn, checkIn);
		nuevaReserva.getPublicacion().agregarReserva(nuevaReserva);
		verify(publicacion, times(1)).agregarReserva(nuevaReserva);
	}
	
	@Test
	void testCuandoUnaReservaSeCreaSeAgregaALaListaDeReservasDelInquilinoCorrespondiente() {
		Reserva nuevaReserva = new Reserva(publicacion, inquilino, checkIn, checkIn);
		nuevaReserva.getInquilino().agregarReserva(nuevaReserva);
		verify(inquilino, times(1)).agregarReserva(nuevaReserva);
	}
	
	@Test
	void testCuandoUnaReservaSeCreaSeNotificaAlPropitarioAlRespecto() {
		Reserva nuevaReserva = new Reserva(publicacion, inquilino, checkIn, checkIn);
		nuevaReserva.notificarNuevaReserva();
		verify(propietario, times(1)).notificarReservaPendiente(nuevaReserva);
	}

}
