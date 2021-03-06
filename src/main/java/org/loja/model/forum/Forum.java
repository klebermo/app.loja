package org.loja.model.forum;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.List;
import org.loja.model.topic.Topic;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Forum extends org.loja.model.Model {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "forum")
  @Fetch(FetchMode.SELECT)
  @JsonIgnore
  private List<Topic> topicos;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<Topic> getTopicos() {
    return topicos;
  }

  public void setTopicos(List<Topic> topicos) {
    this.topicos = topicos;
  }

  @Override
  public String toString() {
    return topicos.toString();
  }
}
