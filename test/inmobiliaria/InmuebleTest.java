package inmobiliaria;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import publicacion.Direccion;
import publicacion.Inmueble;
import sitio.Sitio;
import usuario.Usuario;

class InmuebleTest {

	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Direccion direccion;
	private Usuario propietario;
	private List<String> servicios;
	private Sitio sitio;
	
	@BeforeEach
	void setUp() throws Exception {
		sitio = new Sitio();
		propietario = new Usuario("Kevin", "kevindlp@gmail.com", "+540112223341", LocalDate.of(2019, Month.APRIL, 2), sitio);
		direccion = new Direccion("Rivadavia", 1212);
		servicios = new ArrayList<String>();
		inmueble1 = new Inmueble("Casa", "Argentina", "Quilmes", direccion, servicios, propietario);
		inmueble2 = new Inmueble("Departamento", "Peru", "Quito", direccion, servicios, propietario);
	}
	
	@Test
	void testConstructorInmueble() {
		assertEquals(inmueble1.getTipoInmueble(), "Casa");
		assertEquals(inmueble1.getPais(), "Argentina");
		assertEquals(inmueble1.getCiudad(), "Quilmes"); 
		assertEquals(inmueble1.getDireccion(), direccion);
		assertEquals(inmueble1.getDireccion().getCalle(), "Rivadavia");
		assertEquals(inmueble1.getDireccion().getNumeracion(), 1212);
		assertEquals(inmueble1.getServicios().size(), 0);
		assertEquals(inmueble1.getNombrePropietario(), "Kevin");
	}
	
	@Test
	void testAgregoInmuebleAlPropietario() {
		propietario.agregarInmueble(inmueble1);
		propietario.agregarInmueble(inmueble2);
		assertEquals(inmueble1.getPropietario(), propietario);
		assertEquals(inmueble2.getPropietario(), propietario);
		assertEquals(propietario.getInmuebles().size(), 2);
		assertTrue(propietario.getInmuebles().contains(inmueble1));
		assertTrue(propietario.getInmuebles().contains(inmueble2));
	}
}
