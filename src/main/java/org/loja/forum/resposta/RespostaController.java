package org.loja.forum.resposta;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("resposta")
public class RespostaController extends org.loja.forum.Controller<Resposta> {
  @Autowired
  private RespostaService serv;

  public RespostaController() {
    super(Resposta.class);
  }

  @RequestMapping(value="/save", method=RequestMethod.POST)
  @ResponseBody
  public Integer save_topic(@Valid Resposta object, BindingResult result) {
    return this.serv.save(object);
  }
}
