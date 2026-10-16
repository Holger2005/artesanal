package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Cliente;
import com.krakedev.artesanal.Maquina;
import com.krakedev.artesanal.NegocioMejorado;

public class TestNegocioMejorado {

	private NegocioMejorado negocio;

	@Test
	public void testGenerarCodigo() {
		negocio = new NegocioMejorado();
		String codigo = negocio.generarCodigo();

		assertNotNull(codigo, "El código no debe ser nulo");
		assertTrue(codigo.startsWith("M-"), "El código debe empezar con 'M-'");

		String numeroString = codigo.replace("M-", "");
		int numero = Integer.parseInt(numeroString);
		assertTrue(numero >= 1 && numero <= 100, "El número debe estar entre 1 y 100");
	}

	@Test
	public void testRecuperarMaquina() {
		negocio = new NegocioMejorado();
		Maquina m1 = new Maquina("Roja", "Cerveza roja", 3.5, "M-50");
		negocio.getMaquinas().add(m1);

		Maquina recuperada = negocio.recuperarMaquina("M-50");
		assertNotNull(recuperada, "Debe recuperar la máquina si el código existe");
		assertEquals("Roja", recuperada.getNombreCerveza(), "El nombre debe coincidir");

		Maquina noRecuperada = negocio.recuperarMaquina("M-99");
		assertNull(noRecuperada, "Debe retornar null si la máquina no existe");
	}

	@Test
	public void testAgregarMaquinaValidacion() {
		negocio = new NegocioMejorado();
		boolean agregada = negocio.agregarMaquina("Rubia", "Cerveza suave", 2.0);

		assertTrue(agregada, "Debe retornar true al agregar una máquina nueva");
		assertEquals(1, negocio.getMaquinas().size(), "El tamaño de la lista de máquinas debe ser 1");
	}

	@Test
	public void testConsumirCervezaIntegracion() {
		negocio = new NegocioMejorado();

		negocio.agregarMaquina("Stout", "Cerveza negra", 3.0);
		negocio.registrarCliente("Roberto", "1712345678");

		Maquina maquina = negocio.getMaquinas().get(0);
		String codigoMaquina = maquina.getCódigo();

		negocio.cargarMaquina();

		negocio.consumirCerveza(101, codigoMaquina, 50);

		Cliente cliente = negocio.buscarClientePorCodigo(101);

		assertEquals(150.0, cliente.getTotalConsumido(), 0.01,
				"El cliente debe tener el valor consumido actualizado correctamente");
		assertNotNull(maquina, "La máquina debe seguir existiendo y haber sido afectada");
	}
	
	@Test
    public void testConsultarValorVendido() {
		negocio = new NegocioMejorado();
        negocio.registrarCliente("Carlos", "1111111111");
        negocio.registrarCliente("Maria", "2222222222");

        Cliente c1 = negocio.buscarClientePorCodigo(101);
        Cliente c2 = negocio.buscarClientePorCodigo(102);

        c1.setTotalConsumido(15.5);
        c2.setTotalConsumido(20.0);

        double total = negocio.consultarValorVendido();
        assertEquals(35.5, total, "La suma del valor vendido debe ser exacta a los consumos individuales");
    }
	
	

}