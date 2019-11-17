package inmobiliaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Inmueble;
import publicacion.Reserva;
import sitio.Sitio;
import usuario.Usuario;

class UsuarioTest {
    
	private LocalDate fechaRegistro;
	private Usuario inquilino;
	private Usuario propietario;
	private Inmueble inmueble;
	private Reserva reserva;
	private Sitio sitio;
	
	@BeforeEach
	public void setUp() throws Exception {
		sitio = mock(Sitio.class);
		fechaRegistro = LocalDate.of(2019, Month.APRIL, 21);
		inquilino = new Usuario("Ezequiel", "ese@hotmail.com", "+5411931294", fechaRegistro, sitio);
		propietario = new Usuario("Marcos", "marcos@hotmail.com", "+541194234125", fechaRegistro, sitio);
		inmueble = mock(Inmueble.class);
		reserva = mock(Reserva.class);
		when(reserva.getNombrePropietario()).thenReturn(propietario.getNombre());
		when(reserva.getNombreInquilino()).thenReturn(inquilino.getNombre());
	}
	
	@Test
	void testConstructorPropietario() {
		assertEquals(inquilino.getNombre(), "Ezequiel");
		assertEquals(inquilino.getEmail(), "ese@hotmail.com");
		assertEquals(inquilino.getTelefono(), "+5411931294");
		assertEquals(inquilino.getFechaRegistro(), fechaRegistro);
		assertEquals(inquilino.getInmuebles().size(), 0);
		assertEquals(inquilino.getReservas().size(), 0);
		assertEquals(inquilino.getSitio(), sitio);
	}
	
	@Test
	void testAgregoUnInmuebleAlPropietario() {
		this.propietario.agregarInmueble(inmueble);
		assertTrue(propietario.getInmuebles().contains(inmueble));
	}
	
	@Test
	void testNoSePuedenAgregarDosVecesUnMismoInmueble() {
		this.propietario.agregarInmueble(inmueble);
		this.propietario.agregarInmueble(inmueble);
		assertEquals(propietario.getInmuebles().size(), 1);
	}
	
	@Test
	void testInquilinoAgregaUnaReservaYLoContieneSuListaDeReservas() {
		this.inquilino.agregarReserva(reserva);
		assertTrue(inquilino.getReservas().contains(reserva));
	}

	@Test
	void testAlRealizarReservaSeEnviaMailAlPropietario() {
		propietario.notificarReservaPendiente(reserva);
		verify(sitio, times(1)).enviarMail("marcos@hotmail.com", "Tenes una nueva reserva!", "El usuario Ezequiel ha realizado una reserva en tu inmueble");
	}
	
	@Test
	void testAlAceptarReservaSeEnviaMailAlInquilino() {
		inquilino.notificarReservaConcretada(reserva);
		verify(sitio, times(1)).enviarMail("ese@hotmail.com", "Tu reserva se confirmo!", "El usuario Marcos acepto tu reserva!");
	}
}
