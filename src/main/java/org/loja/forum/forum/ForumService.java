package org.loja.forum.forum;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.produto.ProdutoDao;
import org.loja.model.produto.Produto;

@Service
public class ForumService extends org.loja.forum.Service<Forum> {
  @Autowired
  private ProdutoDao produtoDao;

  public ForumService() {
    super(Forum.class);
  }

  public Produto forum_owner(Integer forum_id) {
    return produtoDao.findBy("forum_id", forum_id);
  }
}
