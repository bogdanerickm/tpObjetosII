package inmobiliaria;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import filtros.FechaInOut;
import filtros.FiltroAND;
import filtros.Ciudad;
import filtros.FiltroCompuesto;
import filtros.FiltroOR;
import publicacion.Inmueble;
import publicacion.Publicacion;
import sitio.Sitio;

class FiltrosTest {

	private Sitio sitio;
	private Ciudad filtroCiudadQuilmes;
	private Ciudad filtroCiudadBernal;
	private Ciudad filtroCiudadSarandi;
	private LocalDate filtroCheckIn;
	private LocalDate filtroCheckOut;
	private FechaInOut filtroFecha;
	private FiltroCompuesto filtroCompuesto1;
	private FiltroCompuesto filtroCompuesto2;
	private FiltroCompuesto filtroCompuesto3;
	private Publicacion publicacion1;
	private Publicacion publicacion2;
	private Publicacion publicacion3;
	private Publicacion publicacion4;
	private Publicacion publicacion5;
	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Inmueble inmueble3;
	private Inmueble inmueble4;
	private Inmueble inmueble5;
	private List<Publicacion> publicacionesFiltradas;

	@BeforeEach
	void setUp() {
		sitio = new Sitio();
		publicacionesFiltradas = sitio.getPublicaciones();
		
		filtroCheckIn = LocalDate.of(2019, Month.SEPTEMBER, 20);
		filtroCheckOut = LocalDate.of(2019, Month.SEPTEMBER, 30);
		
		filtroCiudadQuilmes = new Ciudad("Quilmes");
		filtroCiudadBernal = new Ciudad("Bernal");
		filtroCiudadSarandi = new Ciudad("Sarandi");
		filtroFecha = new FechaInOut(filtroCheckIn, filtroCheckOut);
		filtroCompuesto1 = new FiltroCompuesto();
		filtroCompuesto2 = new FiltroCompuesto();
		filtroCompuesto3 = new FiltroCompuesto();
		
		publicacion1 = mock(Publicacion.class);
		publicacion2 = mock(Publicacion.class);
		publicacion3 = mock(Publicacion.class);
		publicacion4 = mock(Publicacion.class);
		publicacion5 = mock(Publicacion.class);
		
		inmueble1 = mock(Inmueble.class);
		inmueble2 = mock(Inmueble.class);
		inmueble3 = mock(Inmueble.class);
		inmueble4 = mock(Inmueble.class);
		inmueble5 = mock(Inmueble.class);
		
		when(publicacion1.getInmueble()).thenReturn(inmueble1);
		when(inmueble1.getCiudad()).thenReturn("Quilmes");		// coincide
		
		when(publicacion2.getInmueble()).thenReturn(inmueble2);
		when(inmueble2.getCiudad()).thenReturn("Berazategui");
		
		when(publicacion3.getInmueble()).thenReturn(inmueble3);
		when(inmueble3.getCiudad()).thenReturn("Quilmes");		// coincide
		
		when(publicacion4.getInmueble()).thenReturn(inmueble4);
		when(inmueble4.getCiudad()).thenReturn("Quilmes");		// coincide
		
		when(publicacion5.getInmueble()).thenReturn(inmueble5);
		when(inmueble5.getCiudad()).thenReturn("Bernal");		

		
		when(publicacion1.puedeReservarseEn(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		when(publicacion2.puedeReservarseEn(filtroCheckIn, filtroCheckOut)).thenReturn(false);
		when(publicacion3.puedeReservarseEn(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		when(publicacion4.puedeReservarseEn(filtroCheckIn, filtroCheckOut)).thenReturn(false);
		when(publicacion5.puedeReservarseEn(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		
		when(publicacion1.estaDentroDeFecha(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		when(publicacion2.estaDentroDeFecha(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		when(publicacion3.estaDentroDeFecha(filtroCheckIn, filtroCheckOut)).thenReturn(false);
		when(publicacion4.estaDentroDeFecha(filtroCheckIn, filtroCheckOut)).thenReturn(false);
		when(publicacion5.estaDentroDeFecha(filtroCheckIn, filtroCheckOut)).thenReturn(true);
		
		sitio.agregarPublicacion(this.publicacion1);
		sitio.agregarPublicacion(this.publicacion2);
		sitio.agregarPublicacion(this.publicacion3);
		sitio.agregarPublicacion(this.publicacion4);
		sitio.agregarPublicacion(this.publicacion5);
		
	}
	
	@Test
	void testFiltroPorCiudadQuilmesYDevuelveTresPublicaciones() {
		this.filtroCompuesto1.agregarFiltro(filtroCiudadQuilmes);
		publicacionesFiltradas = this.filtroCompuesto1.filtrarPublicaciones(publicacionesFiltradas);
		assertEquals(publicacionesFiltradas.size(), 3);
		assertTrue(publicacionesFiltradas.contains(publicacion1));
		assertFalse(publicacionesFiltradas.contains(publicacion2));
		assertTrue(publicacionesFiltradas.contains(publicacion3));
		assertTrue(publicacionesFiltradas.contains(publicacion4));
		assertFalse(publicacionesFiltradas.contains(publicacion5));
	}
	
	@Test
	void testFiltroPorFechaYMeDevuelveDosPublicacionesQueEsteDentroDelRangoYPuedaReservarseEntreLasFechasDelFiltro() {
		this.filtroCompuesto1.agregarFiltro(filtroFecha);
		publicacionesFiltradas = this.filtroCompuesto1.filtrarPublicaciones(publicacionesFiltradas);
		assertEquals(publicacionesFiltradas.size(), 2);
		assertTrue(publicacionesFiltradas.contains(publicacion1));
		assertFalse(publicacionesFiltradas.contains(publicacion2));
		assertFalse(publicacionesFiltradas.contains(publicacion3));
		assertFalse(publicacionesFiltradas.contains(publicacion4));
		assertTrue(publicacionesFiltradas.contains(publicacion5));
	}
	
	@Test
	void testFiltroPorFechaYCiudadYMeDevuelveUnaPublicacionQueEsteDentroDelRangoYPuedaReservarseEntreLasFechasDelFiltroEnQuilmes() {
		this.filtroCompuesto1.agregarFiltro(filtroCiudadQuilmes);
		this.filtroCompuesto1.agregarFiltro(filtroFecha);
		publicacionesFiltradas = this.filtroCompuesto1.filtrarPublicaciones(publicacionesFiltradas);
		assertEquals(publicacionesFiltradas.size(), 1);
		assertTrue(publicacionesFiltradas.contains(publicacion1));
		assertFalse(publicacionesFiltradas.contains(publicacion2));
		assertFalse(publicacionesFiltradas.contains(publicacion3));
		assertFalse(publicacionesFiltradas.contains(publicacion4));
		assertFalse(publicacionesFiltradas.contains(publicacion5));
	}
	
	@Test
	void testAgregoDosFiltrosCompuestosAUnFiltroCompuestoYNoMeDevuelveNingunaPublicacion() {
		this.filtroCompuesto1.agregarFiltro(filtroCiudadQuilmes);
		this.filtroCompuesto1.agregarFiltro(filtroFecha);
		this.filtroCompuesto2.agregarFiltro(filtroCiudadSarandi);
		this.filtroCompuesto2.agregarFiltro(filtroFecha);
		this.filtroCompuesto3.agregarFiltro(filtroCompuesto1);
		this.filtroCompuesto3.agregarFiltro(filtroCompuesto2);
		publicacionesFiltradas = this.filtroCompuesto3.filtrarPublicaciones(publicacionesFiltradas);
		assertEquals(publicacionesFiltradas.size(), 0);
		assertFalse(publicacionesFiltradas.contains(publicacion1));
		assertFalse(publicacionesFiltradas.contains(publicacion2));
		assertFalse(publicacionesFiltradas.contains(publicacion3));
		assertFalse(publicacionesFiltradas.contains(publicacion4));
		assertFalse(publicacionesFiltradas.contains(publicacion5));
	}
	
	@Test
	void testBuscarPublicacionesDirectamenteDesdeElSitio() {
		assertEquals(sitio.buscarPublicaciones(filtroCiudadQuilmes).size(), 3);
		assertTrue(sitio.buscarPublicaciones(filtroCiudadQuilmes).contains(publicacion1));
		assertTrue(sitio.buscarPublicaciones(filtroCiudadQuilmes).contains(publicacion3));
		assertTrue(sitio.buscarPublicaciones(filtroCiudadQuilmes).contains(publicacion4));
		assertFalse(sitio.buscarPublicaciones(filtroCiudadQuilmes).contains(publicacion2));
		assertFalse(sitio.buscarPublicaciones(filtroCiudadQuilmes).contains(publicacion5));
	}
	
	@Test
	void testBuscarPublicacionesDirectamenteDesdeElSitioUsandoFiltroCompuesto() {
		this.filtroCompuesto1.agregarFiltro(filtroCiudadQuilmes);
		this.filtroCompuesto1.agregarFiltro(filtroFecha);
		assertEquals(sitio.buscarPublicaciones(filtroCompuesto1).size(), 1);
		assertTrue(sitio.buscarPublicaciones(filtroCompuesto1).contains(publicacion1));
		assertFalse(sitio.buscarPublicaciones(filtroCompuesto1).contains(publicacion3));
		assertFalse(sitio.buscarPublicaciones(filtroCompuesto1).contains(publicacion4));
		assertFalse(sitio.buscarPublicaciones(filtroCompuesto1).contains(publicacion2));
		assertFalse(sitio.buscarPublicaciones(filtroCompuesto1).contains(publicacion5));
	}
	
	@Test
	void testFiltroOrConDosCiudades() {
		FiltroOR filtroOR = new FiltroOR(filtroCiudadQuilmes, filtroCiudadBernal);
		this.filtroCompuesto1.agregarFiltro(filtroOR);
		assertEquals(sitio.buscarPublicaciones(filtroCompuesto1).size(), 4);
	}
	
	@Test
	void testFiltroAndConUnaCiudadYUnaFecha() {
		FiltroAND filtroAnd = new FiltroAND(filtroCiudadQuilmes, filtroFecha);
		this.filtroCompuesto1.agregarFiltro(filtroAnd);
		assertEquals(sitio.buscarPublicaciones(filtroCompuesto1).size(), 1);
	}
}
