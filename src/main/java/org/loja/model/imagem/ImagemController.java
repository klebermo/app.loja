package org.loja.model.imagem;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

@Controller
@RequestMapping("imagem")
public class ImagemController extends org.loja.model.Controller<Imagem> {
  @Autowired
  private ImagemService serv;

  public ImagemController() {
    super(Imagem.class);
  }

  @RequestMapping(value="upload", method=RequestMethod.POST)
  @ResponseBody
  public Integer upload(@RequestParam("bytes") String bytes, @RequestParam("type") String type) throws IOException {
    return this.serv.upload(bytes, type);
  }

  @RequestMapping("download/{id}")
  @ResponseBody
  public byte[] download(@PathVariable("id") Integer id) throws IOException {
    return this.serv.download(id);
  }

  @RequestMapping("remove/{id}")
  @ResponseBody
  public void remove(@PathVariable("id") Integer id) throws IOException {
    this.serv.remove(id);
  }
}
