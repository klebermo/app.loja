package org.loja.model.arquivo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
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
  public Integer upload(@RequestParam("bytes") String arquivo, @RequestParam("type") String type) throws IOException {
    return this.serv.upload(arquivo, type);
  }

  @RequestMapping("download/{id}")
  public HttpEntity<byte[]> download(@PathVariable("id") Integer id) throws IOException {
    return this.serv.download(id);
  }

  @RequestMapping("remove/{id}")
  @ResponseBody
  public void remove(@PathVariable("id") Integer id) throws IOException {
    this.serv.remove(id);
  }
}
