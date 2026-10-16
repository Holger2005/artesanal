package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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

}