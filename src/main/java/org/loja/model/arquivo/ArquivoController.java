package org.loja.model.arquivo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpEntity;

@Controller
@RequestMapping("arquivo")
public class ArquivoController extends org.loja.model.Controller<Arquivo> {
  @Autowired
  private ArquivoService serv;

  public ArquivoController() {
    super(Arquivo.class);
  }

  @RequestMapping(value="upload", method=RequestMethod.POST)
  @ResponseBody
  public Integer upload(@RequestParam("name") String name, @RequestParam("bytes") String arquivo, @RequestParam("type") String type) throws Exception {
    return this.serv.upload(name, arquivo, type);
  }

  @RequestMapping("download/{id}")
  public HttpEntity<byte[]> download(@PathVariable("id") Integer arquivo_id) throws Exception {
    return this.serv.download(arquivo_id);
  }

  @RequestMapping("edit/{id}")
  @ResponseBody
  public void edit(@PathVariable("id") Integer id, @RequestParam("name") String name, @RequestParam("bytes") String arquivo, @RequestParam("type") String type) throws Exception {
    this.serv.edit(id, name, arquivo, type);
  }

  @RequestMapping("remove/{id}")
  @ResponseBody
  public void remove(@PathVariable("id") Integer id) throws Exception {
    this.serv.remove(id);
  }
}
