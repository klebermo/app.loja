package org.loja.model;

public abstract class Model extends Object {
  public abstract Object getId();

  public boolean equals(Model object) {
    return toString().equals(object.toString());
  }

  public abstract String toString();
}
