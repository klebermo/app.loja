package org.loja.model;

public abstract class Model extends Object {
  public abstract Integer getId();

  @Override
  public boolean equals(Object object) {
    Model other = (Model)object;
    return this.toString().equals(other.toString());
  }

  public abstract String toString();
}
