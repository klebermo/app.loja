package org.loja.settings.idiomas;

import java.util.List;

public class Idiomas extends org.loja.settings.Model {
  private static final long serialVersionUID = 2L;

  private List<String> listaDeIdiomas;

  public List<String> getListaDeIdiomas() {
    return listaDeIdiomas;
  }

  public void setListaDeIdiomas(List<String> listaDeIdiomas) {
    this.listaDeIdiomas = listaDeIdiomas;
  }

  @Override
	public String toString() {
		return "idiomas";
	}
}
