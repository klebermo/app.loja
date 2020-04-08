package org.loja.model.topic;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.model.resposta.Resposta;
import org.loja.model.resposta.RespostaDao;

@Service
public class TopicService extends org.loja.model.Service<Topic> {
  public TopicService() {
    super(Topic.class);
  }

  @Autowired
  private RespostaDao respostaDao;

  public void add_resposta(Integer topic_id, Resposta resposta_data) {
    Topic topic = this.dao.findBy("id", topic_id);
    Resposta resposta = respostaDao.insert(resposta_data);
    topic.setResposta(resposta);
    this.dao.update(topic);
  }
}
