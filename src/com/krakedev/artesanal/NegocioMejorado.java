package com.krakedev.artesanal;

import java.util.ArrayList;

public class NegocioMejorado {

	ArrayList<Maquina> maquinas;
	private ArrayList<Cliente> clientes = new ArrayList<>();
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

	public Cliente buscarClientePorCedula(String cedula) {

		for (int i = 0; i < clientes.size(); i++) {

			Cliente cliente = clientes.get(i);

			if (cedula != null && cliente.getCedula().equals(cedula)) {
				return cliente;
			}

		}

		return null;
	}

	public Cliente buscarClientePorCodigo(int codigo) {

		for (int i = 0; i < clientes.size(); i++) {

			Cliente cliente = clientes.get(i);

			if (cliente.getCodigo() == codigo) {

				return cliente;

			}

		}

		return null;
	}

	public void consumirCerveza(int codigoCliente, String codigoMaquina, double cantidad) {

		Maquina mr = recuperarMaquina(codigoMaquina);
		Cliente cr = buscarClientePorCodigo(codigoCliente);

		if (mr != null && cr != null) {
			double cervezaServida = mr.servirCerveza(cantidad);
			registrarConsumo(cr, cervezaServida);
		}

	}

	public void registrarConsumo(Cliente cliente, double valorConsumido) {

		double consumoActual = cliente.getTotalConsumido();
		cliente.setTotalConsumido(consumoActual + valorConsumido);

	}
}