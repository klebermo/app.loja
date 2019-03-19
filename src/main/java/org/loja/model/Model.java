package org.loja.model;

public abstract class Model extends Object {
  public abstract Integer getId();

  @Override
  public boolean equals(Object object) {
    Model other = (Model)object;
    System.out.println("self: "+toString());
    System.out.println("other: "+other.toString());
    return toString().equals(other.toString());
  }

  @Override
  public int hashCode() {
      int hash = 7;
      hash = 31 * hash + (int) getId();
      hash = 31 * hash + (toString() == null ? 0 : toString().hashCode());
      return hash;
  }

  public abstract String toString();
}
