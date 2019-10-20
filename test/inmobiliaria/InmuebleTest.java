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
import usuario.Propietario;

class InmuebleTest {

	private Inmueble inmueble1;
	private Inmueble inmueble2;
	private Direccion direccion;
	private Propietario propietario;
	private List<String> servicios;

	@BeforeEach
	void setUp() throws Exception {
		propietario = new Propietario("Kevin", "kevindlp@gmail.com", "+540112223341", LocalDate.of(2019, Month.APRIL, 2));
		direccion = new Direccion("Rivadavia", 1212);
		servicios = new ArrayList<String>();
		inmueble1 = new Inmueble("Casa", "Argentina", "Quilmes", direccion, servicios);
		inmueble2 = new Inmueble("Departamento", "Peru", "Quito", direccion, servicios);
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
	}
	
	@Test
	void testAgregoInmuebleAlPropietario() {
		inmueble1.setPropietario(propietario);
		inmueble2.setPropietario(propietario);
		assertEquals(inmueble1.getPropietario(), propietario);
		assertEquals(inmueble2.getPropietario(), propietario);
		assertEquals(propietario.getInmuebles().size(), 2);
		assertTrue(propietario.getInmuebles().contains(inmueble1));
		assertTrue(propietario.getInmuebles().contains(inmueble2));
	}
}
