package org.loja.model.pagina.PageTree;

import java.util.List;
import java.util.ArrayList;
import org.loja.model.pagina.Pagina;

public class Node {
  private Node parent;

  private Pagina content;

  private List<Node> children;

  public Node() {
    this.parent = null;
    this.content = null;
    this.children = new ArrayList<Node>();
  }

  public Node(Pagina content) {
    if(content.getParent() != null)
      this.parent = new Node(content.getParent());
    else
      this.parent = null;
    this.content = content;
    this.children = new ArrayList<Node>();
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public Pagina getContent() {
    return content;
  }

  public void setPagina(Pagina content) {
    this.content = content;
  }

  public List<Node> getChildren() {
    return children;
  }

  public void setChildren(List<Node> children) {
    this.children = children;
  }

  public void insertChild(Pagina child) {
    if(children == null)
      children = new ArrayList<Node>();
    children.add(new Node(child));
  }

  public void removeChild(Pagina child) {
    int i;
    for(i=0; i<children.size(); i++)
      if(children.get(i).equals(child))
        break;
    children.remove(i);
  }
}
