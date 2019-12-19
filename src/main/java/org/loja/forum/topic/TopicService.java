package org.loja.forum.topic;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.loja.forum.resposta.RespostaDao;
import org.loja.forum.resposta.Resposta;
import org.loja.MailSender;

@Service
public class TopicService extends org.loja.forum.Service<Topic> {
  @Autowired
  private RespostaDao respostaDao;

  @Autowired
  private MailSender mailSender;

  public TopicService() {
    super(Topic.class);
  }

  public Topic save(Topic object) {
    this.dao.insert(object);
    return object;
  }

  public void add_resposta(Integer topic_id, Integer resposta_id) {
    Topic topic = this.dao.findBy("id", topic_id);
    Resposta resposta = respostaDao.findBy("id", resposta_id);
    topic.setResposta(resposta);
    this.dao.update(topic);
    //mailSender.send_mail(topic.getUsuario().getEmail(), topic.getUsuario().getFirstName(), topic.getUsuario().getLastName(), "Pergunta respondida", "...");
  }

  public Integer unread() {
    List<Topic> all = this.dao.select();
    List<Topic> unread = new ArrayList<Topic>();
    for(Topic t : all) {
      if(t.getResposta() == null)
        unread.add(t);
    }
    return unread.size();
  }
}
