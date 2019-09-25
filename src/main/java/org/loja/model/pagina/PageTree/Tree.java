package org.loja.model.pagina.PageTree;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.loja.model.pagina.Pagina;

public class Tree {
  private Node root;

  public Tree() {
    this.root = new Node();
  }

  public Tree(List<Pagina> paginas) {
    this.root = new Node();
    for(Pagina pagina : paginas)
      this.insert(pagina);
  }

  public Node getRoot() {
    return root;
  }

  public void setRoot(Node root) {
    this.root = root;
  }

  public void insert(Pagina content) {
    if(content.getParent() == null)
      root.getChildren().add(new Node(content));
    else {
      this.searchNode(content.getParent()).insertChild(content);
    }
  }

  public Node searchNode(Pagina pagina) {
    Node current = root;
    for(Node child : current.getChildren()) {
      if(child.getContent().equals(pagina))
        return child;
      else
        return searchNode(pagina, child);
    }
    return current;
  }

  public Node searchNode(Pagina pagina, Node current) {
    for(Node child : current.getChildren()) {
      if(child.getContent().equals(pagina))
        return child;
      else
        return searchNode(pagina, child);
    }
    return current;
  }

  public List<Node> search(Pagina pagina) {
    List<Node> ret = new ArrayList<Node>();
    Node current = root;
    for(Node child : current.getChildren()) {
      if(child.getContent().equals(pagina))
        ret.add(child);
      else
        ret.addAll(search(pagina, child, false));
    }
    return ret;
  }

  public List<Node> search(Pagina pagina, Node current, boolean related) {
    List<Node> ret = new ArrayList<Node>();
    if(related) {
      ret.add(current);
      if(current != null && current.getParent() != null)
        ret.addAll(search(pagina, current.getParent(), true));
      return ret;
    }
    for(Node child : current.getChildren()) {
      if(child.getContent().equals(pagina))
        ret.addAll(search(pagina, child, true));
      else
        ret.addAll(search(pagina, child, false));
    }
    return ret;
  }
}
