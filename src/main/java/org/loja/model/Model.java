package org.loja.model;

public abstract class Model extends Object {
  public abstract Object getId();

  @Override
  public boolean equals(Object object) {
    System.out.println("self: "+this.toString());
    System.out.println("other: "+object.toString());
    return toString().equals(object.toString());
  }

  public abstract String toString();
}
