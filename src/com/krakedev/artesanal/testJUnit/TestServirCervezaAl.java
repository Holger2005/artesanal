package com.krakedev.artesanal.testJUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.krakedev.artesanal.Maquina;

public class TestServirCervezaAl {

	private static final double DELTA = 0.0001;

	@Test
	void testServirCervezaConCantidadSuficiente() {
		// Valida que, cuando existe suficiente cerveza,
		// se descuenta la cantidad solicitada y se retorna el valor a pagar.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.01, 1000);

		maquina.recargarCerveza(500);

		double valor = maquina.servirCerveza(200);

		assertEquals(2.00, valor, DELTA);
		assertEquals(300.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaCuandoCantidadSolicitadaEsExactamenteLaDisponible() {
		// Valida que se pueda servir toda la cerveza disponible
		// y que la cantidad actual quede en cero.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.02, 1000);

		maquina.recargarCerveza(500);

		double valor = maquina.servirCerveza(500);

		assertEquals(10.00, valor, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaCuandoNoHayCantidadSuficiente() {
		// Valida que, cuando la máquina no tiene suficiente cerveza,
		// no se sirva nada y se retorne cero.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.01, 1000);

		maquina.recargarCerveza(300);

		double valor = maquina.servirCerveza(500);

		assertEquals(0.0, valor, DELTA);
		assertEquals(300.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaSinCervezaDisponible() {
		// Valida que, cuando la máquina está vacía,
		// no se pueda servir cerveza y la cantidad actual permanezca en cero.
		Maquina maquina = new Maquina("Pilsener", "Cerveza artesanal", 0.01, 1000);

		double valor = maquina.servirCerveza(200);

		assertEquals(0.0, valor, DELTA);
		assertEquals(0.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaConConstructorDeTresParametros() {
		// Valida el funcionamiento de servirCerveza usando
		// el constructor que recibe nombre, descripción y precio.
		Maquina maquina = new Maquina("Stout", "Cerveza oscura", 0.015);

		maquina.recargarCerveza(500);

		double valor = maquina.servirCerveza(200);

		assertEquals(3.00, valor, DELTA);
		assertEquals(300.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaConPrecioPorMililitroDiferente() {
		// Valida que el valor a pagar dependa de la cantidad servida
		// y del precio por mililitro configurado en la máquina.
		Maquina maquina = new Maquina("IPA", "Cerveza tipo IPA", 0.025, 1000);

		maquina.recargarCerveza(600);

		double valor = maquina.servirCerveza(400);

		assertEquals(10.00, valor, DELTA);
		assertEquals(200.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaConCantidadDisponibleLimite() {
		// Valida que se pueda servir correctamente una cantidad
		// que está dentro de la cerveza disponible.
		Maquina maquina = new Maquina("Lager", "Cerveza ligera", 0.01, 1000);

		maquina.recargarCerveza(900);

		double valor = maquina.servirCerveza(899);

		assertEquals(8.99, valor, DELTA);
		assertEquals(1.0, maquina.getCantidadActual(), DELTA);
	}

	@Test
	void testServirCervezaNoModificaCantidadCuandoNoPuedeServir() {
		// Valida específicamente que, ante una solicitud superior
		// a la cantidad disponible, la cantidad actual no se modifique.
		Maquina maquina = new Maquina("Amber", "Cerveza roja", 0.02, 1000);

		maquina.recargarCerveza(400);
		double cantidadAntes = maquina.getCantidadActual();

		double valor = maquina.servirCerveza(401);

		assertEquals(0.0, valor, DELTA);
		assertEquals(cantidadAntes, maquina.getCantidadActual(), DELTA);
	}
}
