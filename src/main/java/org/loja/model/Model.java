package org.loja.model;

public abstract class Model extends Object {
  public abstract Object getId();

  @Override
  public boolean equals(Object object) {
    return toString().equals(object.toString());
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  public abstract String toString();
}
