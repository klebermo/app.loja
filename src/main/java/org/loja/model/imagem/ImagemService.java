package org.loja.model.imagem;

import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.UUID;
import java.util.Base64;

@Service
public class ImagemService extends org.loja.model.Service<Imagem> {
  private String file_path = System.getProperty("user.home")+File.separator+".app"+File.separator+"Pictures";

  public ImagemService() {
    super(Imagem.class);
  }

  public Integer upload(String bytes, String type) throws IOException {
    BufferedImage src = ImageIO.read(new ByteArrayInputStream(Base64.getDecoder().decode(bytes)));

    String file_name = file_path + File.separator + fileName();
    File arquivo = new File(file_name);
		if(!arquivo.exists())
			if(arquivo.mkdirs())
				arquivo.createNewFile();

    ImageIO.write(src, type, arquivo);

    Imagem imagem = new Imagem();
    imagem.setFileName(file_name);
    this.dao.insert(imagem);

    return imagem.getId();
  }

  public byte[] download(Integer id) throws IOException {
    Imagem imagem = this.dao.findBy("id", id);

    File arquivo = new File(imagem.getFileName());
		InputStream in = new FileInputStream(arquivo);

    return IOUtils.toByteArray(in);
  }

  public void remove(Integer id) throws IOException {
    Imagem imagem = this.dao.findBy("id", id);

    File arquivo = new File(imagem.getFileName());
    if(arquivo.delete())
      this.dao.delete(imagem);
  }

  private String fileName() {
    return UUID.randomUUID().toString();
  }

}
