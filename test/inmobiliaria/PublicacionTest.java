package inmobiliaria;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Inmueble;
import publicacion.Publicacion;
import publicacion.Reserva;
import usuario.Usuario;

import static org.mockito.Mockito.*;

class PublicacionTest {
	
	private Usuario propietario;
	private Publicacion publicacion;
	private Inmueble inmueble;
	private LocalDate checkInPublicacion;
	private LocalDate checkOutPublicacion;
	private LocalDate checkInReserva1;
	private LocalDate checkOutReserva1;
	private LocalDate checkInReserva2;
	private LocalDate checkOutReserva2;
	private LocalDate checkInReserva3;
	private LocalDate checkOutReserva3;
	private Reserva nuevaReserva;
	private Reserva nuevaReserva2;
	private Reserva nuevaReserva3;
	private LocalDate checkInReserva4;
	private LocalDate checkOutReserva4;
	private Reserva nuevaReserva4;

	@BeforeEach
	void setUp() {
		propietario = mock(Usuario.class);
		inmueble = mock(Inmueble.class);
		nuevaReserva = mock(Reserva.class);
		nuevaReserva2 = mock(Reserva.class);
		nuevaReserva3 = mock(Reserva.class);
		nuevaReserva4 = mock(Reserva.class);
		
		checkInPublicacion = LocalDate.of(2019, Month.SEPTEMBER, 25);
		checkOutPublicacion = LocalDate.of(2019, Month.OCTOBER, 5);
		checkInReserva1 = LocalDate.of(2019, Month.SEPTEMBER, 25);
		checkOutReserva1 = LocalDate.of(2019, Month.SEPTEMBER, 29);
		checkInReserva2 = LocalDate.of(2019, Month.OCTOBER, 2);
		checkOutReserva2 = LocalDate.of(2019, Month.OCTOBER, 4);
		checkInReserva3 = LocalDate.of(2019, Month.SEPTEMBER, 29);
		checkOutReserva3 = LocalDate.of(2019, Month.OCTOBER, 2);
		checkInReserva4 = LocalDate.of(2019, Month.SEPTEMBER, 28);
		checkOutReserva4 = LocalDate.of(2019, Month.OCTOBER, 7);
		
		
		when(nuevaReserva.getCheckIn()).thenReturn(checkInReserva1);
		when(nuevaReserva.getCheckOut()).thenReturn(checkOutReserva1);
		when(nuevaReserva.estaAceptada()).thenReturn(true);
		
		when(nuevaReserva2.getCheckIn()).thenReturn(checkInReserva2);
		when(nuevaReserva2.getCheckOut()).thenReturn(checkOutReserva2);
		when(nuevaReserva2.estaAceptada()).thenReturn(true);
		
		when(nuevaReserva3.getCheckIn()).thenReturn(checkInReserva3);
		when(nuevaReserva3.getCheckOut()).thenReturn(checkOutReserva3);
		when(nuevaReserva3.estaAceptada()).thenReturn(true);

		when(nuevaReserva4.getCheckIn()).thenReturn(checkInReserva4);
		when(nuevaReserva4.getCheckOut()).thenReturn(checkOutReserva4);
		when(nuevaReserva4.estaAceptada()).thenReturn(true);
		
		publicacion = new Publicacion(inmueble, 4, checkInPublicacion, checkOutPublicacion, 10000d);
	}
	
	@Test
	void testConstructorPublicacion() {
		assertEquals(publicacion.getInmueble(), inmueble);
		assertEquals(publicacion.getCapacidad(), 4);
		assertEquals(publicacion.getCheckIn(), checkInPublicacion);
		assertEquals(publicacion.getCheckOut(), checkOutPublicacion);
		assertTrue(publicacion.getCheckIn().isBefore(publicacion.getCheckOut()));
		assertEquals(publicacion.getPrecio(), 10000d);
		when(inmueble.getPropietario()).thenReturn(propietario);
		assertEquals(publicacion.getPropietario(), propietario);
		assertEquals(publicacion.getNombrePropietario(), propietario.getNombre());
	}
	
	@Test
	void testCuandoAceptoUnaReservaNoQuieroPoderReservarNuevamenteParaEsasMismasFechas() {
		assertTrue(publicacion.puedeReservarseEn(checkInReserva1, checkOutReserva1));
		publicacion.agregarReserva(nuevaReserva);
		assertFalse(publicacion.puedeReservarseEn(checkInReserva1, checkOutReserva1));
		
		assertTrue(publicacion.puedeReservarseEn(checkInReserva2, checkOutReserva2));
		publicacion.agregarReserva(nuevaReserva2);
		assertFalse(publicacion.puedeReservarseEn(checkInReserva2, checkOutReserva2));
		
		assertTrue(publicacion.puedeReservarseEn(checkInReserva3, checkOutReserva3));
		assertFalse(publicacion.puedeReservarseEn(checkInReserva4, checkOutReserva4));
	}
	
	@Test
	void testQueVerificaQueLasFechasIngresadasEstanDentroDeLaPublicacion() {
		assertTrue(publicacion.estaDentroDeFecha(checkInReserva1, checkOutReserva1));
		assertTrue(publicacion.estaDentroDeFecha(checkInReserva2, checkOutReserva2));
		assertTrue(publicacion.estaDentroDeFecha(checkInReserva3, checkOutReserva3));
		assertFalse(publicacion.estaDentroDeFecha(checkInReserva4, checkOutReserva4));
	}
	
}