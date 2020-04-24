package org.loja.model.registro;

import org.loja.model.cliente.Cliente;
import org.loja.model.produto.Produto;
import org.loja.model.maquina.Maquina;

public class RegistroWrapper {
  private Cliente cliente;

  private Produto produto;

  private Maquina maquina;


	/**
	* Default empty RegistroWrapper constructor
	*/
	public RegistroWrapper() {
		super();
	}

	/**
	* Default RegistroWrapper constructor
	*/
	public RegistroWrapper(Cliente cliente, Produto produto, Maquina maquina) {
		super();
		this.cliente = cliente;
		this.produto = produto;
		this.maquina = maquina;
	}

	/**
	* Returns value of cliente
	* @return
	*/
	public Cliente getCliente() {
		return cliente;
	}

	/**
	* Sets new value of cliente
	* @param
	*/
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	* Returns value of produto
	* @return
	*/
	public Produto getProduto() {
		return produto;
	}

	/**
	* Sets new value of produto
	* @param
	*/
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	/**
	* Returns value of maquina
	* @return
	*/
	public Maquina getMaquina() {
		return maquina;
	}

	/**
	* Sets new value of maquina
	* @param
	*/
	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	/**
	* Create string representation of RegistroWrapper for printing
	* @return
	*/
	@Override
	public String toString() {
		return "RegistroWrapper [cliente=" + cliente + ", produto=" + produto + ", maquina=" + maquina + "]";
	}
}
