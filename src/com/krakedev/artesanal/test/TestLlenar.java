package com.krakedev.artesanal.test;

import com.krakedev.artesanal.Maquina;

public class TestLlenar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Maquina rubia = new Maquina("Pilsenar", "Cerveza Fria", 0.02, 8000, "123");

		rubia.imprimir();

		rubia.LlenarMaquina();

		rubia.imprimir();

		Maquina negra = new Maquina("Club", "Cerveza Buena", 0.03, "123");

		negra.imprimir();

		negra.LlenarMaquina();

		negra.imprimir();

	}

}
