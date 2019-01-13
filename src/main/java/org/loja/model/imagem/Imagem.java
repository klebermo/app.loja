package org.loja.model.imagem;

import org.loja.model.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Imagem extends Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String fileName;

  @Override
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String toString() {
    return fileName;
  }
}
