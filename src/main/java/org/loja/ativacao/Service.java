package org.loja.ativacao;

public class Service<E> {
  private Class<E> classe;

	public Service(Class<E> classe) {
		this.classe = classe;
	}
}
