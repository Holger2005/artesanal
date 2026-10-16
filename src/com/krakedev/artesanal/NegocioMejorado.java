package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {

	ArrayList<Maquina> maquinas;
	private ArrayList<Cliente> clientes;
	private int ultimoCodigo = 100;

	public NegocioMejorado() {
		maquinas = new ArrayList<Maquina>();
	}

	public ArrayList<Maquina> getMaquinas() {
		return maquinas;
	}

	public void setMaquinas(ArrayList<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public String generarCodigo() {

		int numeroAleatorio = (int) (Math.random() * 100) + 1;
		String codigo = "M-" + numeroAleatorio;
		return codigo;

	}

	public boolean agregarMaquina(String nombreCerveza, String descripcion, double precio) {

		String codigo = generarCodigo();

		Maquina mr = recuperarMaquina(codigo);

		if (mr == null) {
			Maquina m1 = new Maquina(nombreCerveza, descripcion, precio, codigo);
			maquinas.add(m1);
			return true;
		}

		return false;

	}

	public void cargarMaquina() {

		for (int i = 0; i < maquinas.size(); i++) {

			Maquina maquina = maquinas.get(i);
			maquina.LlenarMaquina();

		}
	}

	public Maquina recuperarMaquina(String codigo) {

		for (int i = 0; i < maquinas.size(); i++) {
			Maquina maquina = maquinas.get(i);

			if (codigo != null && maquina.getCódigo().equals(codigo)) {

				return maquina;
			}

		}
		return null;
	}

	public void registrarCliente(String nombre, String cedula) {

		ultimoCodigo++;

		Cliente nuevoCliente = new Cliente(nombre, cedula);
		nuevoCliente.setCodigo(ultimoCodigo);

		clientes.add(nuevoCliente);

	}
}