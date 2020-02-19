package org.loja.ativacao;

public class Controller<E> {
  private Class<E> classe;

	public Controller(Class<E> classe) {
		this.classe = classe;
	}
}
