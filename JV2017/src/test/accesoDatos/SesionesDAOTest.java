/** 
 * Proyecto: Juego de la vida.
 * Prueba Junit4 de la clase SesionesDAO.
 * @since: prototipo 2.1
 * @source: SesionesDAOTest.java 
 * @version: 2.1 - 2018.06.11
 * @author: DAM GRUPO 3 Ricardo Esteban Vivancos
 * 
 */
package test.accesoDatos;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;

import accesoDatos.Datos;
import accesoDatos.DatosException;
import modelo.Mundo;
import modelo.Sesion;
import modelo.Sesion.EstadoSesion;
import util.Fecha;

public class SesionDAOTest {

	private static Datos fachada;
	private Sesion sesionPrueba;
	
	/**
	 * Creacion de la fachada
	 */
	@BeforeClass
	public static void crearFachadaDatos() {
		try {
			fachada = new Datos();
		}
		catch (DatosException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Cargar datos de prueba
	 * @author DAM GRUPO 3 Ricardo Esteban Vivancos
	 */
	@Before
	public void crearDatosPrueba() {
		try {
			sesionPrueba= new Sesion(fachada.obtenerUsuario("III1R"), new  Fecha(), 
					new Mundo(), EstadoSesion.PREPARADA);
		}
		catch(DatosException e){
			e.printStackTrace();
		}		
	}

	/**
	 * Borrar datos de prueba antes de cada ejecucion
	 * @author DAM GRUPO 3 Miguel Angel Hernandez Sanchez
	 */
	@After
	public void borraDatosPrueba() {
		fachada.borrarTodasSesiones();
		sesionPrueba = null;
	}
	@Test
	public void testObtenerSimulacion() {
		try {
			fachada.altaSimulacion(simulacionPrueba);
			// Busca la misma Simulacion almacenada.
			assertSame(simulacionPrueba, fachada.obtenerSimulacion(simulacionPrueba));
		} 
		catch (DatosException e) {
		}
	}

	@Test
	public void testObtenerSimulacionPredeterminada() {
		try {
			Usuario usrPredeterminado = UsuariosDAO.getInstancia().obtener("III1R");
			Mundo mundoPredeterminado = MundosDAO.getInstancia().obtener("Demo0");
			Simulacion simulacionPredeterminada = new Simulacion(usrPredeterminado, 
					new Fecha(2005, 05, 05), mundoPredeterminado, 
					EstadoSimulacion.PREPARADA);
			assertEquals(simulacionPredeterminada, fachada.obtenerSimulacion("III1R:20050505000000"));
		} 
		catch (DatosException e) { 
		}	
	}
	
	@Test
	public void testAltaSimulacion() {
		try {
			// Simulacion nueva, que no existe.
			fachada.altaSimulacion(simulacionPrueba);
			// Busca el mismo Simulacion almacenado.
			assertSame(simulacionPrueba, fachada.obtenerSimulacion(simulacionPrueba));
		} 
		catch (DatosException e) { 
		}
	}
} // class
