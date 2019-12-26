package org.loja.model;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;

public abstract class Service<E> {
  protected Class<E> clazz;

  @Autowired
  protected Dao<E> dao;

  public Service(Class<E> clazz) {
    this.clazz = clazz;
  }

  public E insert(E object) {
    return dao.insert(object);
  }

  public E update(E object) {
    return dao.update(object);
  }

  public E delete(E object) {
    return dao.delete(object);
  }

  public List<E> select() {
    return this.dao.select();
  }

  public E findBy(String key, Object value) {
    return this.dao.findBy(key, value);
  }

  public List<E> select(Integer pagina, Integer itemPorPagina) {
    List lista = this.dao.select();
    Integer max = lista.size();
    Integer total_paginas = new Double(Math.ceil(max / itemPorPagina)).intValue() + 1;

    if(pagina > total_paginas)
      pagina = total_paginas;

    Integer start = (pagina - 1) * itemPorPagina;
    Integer end = start + (itemPorPagina - 1);

    if(end > max)
      end = max;

    return lista.subList(start, end);
  }

  public List<E> search(String keyword, Integer pagina, Integer itemPorPagina) throws NoSuchFieldException {
    List<E> lista = this.dao.search(keyword);
    Integer max = lista.size();
    Integer total_paginas = new Double(Math.ceil(max / itemPorPagina)).intValue() + 1;

    if(pagina > total_paginas)
      pagina = total_paginas;

    Integer start = (pagina - 1) * itemPorPagina;
    Integer end = start + (itemPorPagina - 1);

    if(end > max)
      end = max;

    return lista.subList(start, end);
  }

  public E newObject() throws InstantiationException, IllegalAccessException {
    return clazz.newInstance();
  }
}
