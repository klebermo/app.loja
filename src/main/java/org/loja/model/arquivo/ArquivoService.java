package org.loja.model.arquivo;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.UUID;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ArquivoService extends org.loja.model.Service<Arquivo> {
  private String file_path = System.getProperty("user.home")+File.separator+".app"+File.separator+"Uploads";

  public ArquivoService() {
    super(Arquivo.class);
  }

  public Integer upload(String bytes, String type) throws IOException {
    byte[] bytes_final = Base64.getDecoder().decode(bytes);
    String file_name = file_path + File.separator + fileName();

    File path = new File(file_path);
    if(!path.exists())
      path.mkdirs();

    File file = new File(file_name);
		if(!file.exists())
			file.createNewFile();

    FileOutputStream out = new FileOutputStream(file);
    out.write(bytes_final);

    Arquivo arquivo = new Arquivo();
    arquivo.setFileName(file_name);
    arquivo.setType(type);
    this.dao.insert(arquivo);

    return arquivo.getId();
  }

  public HttpEntity<byte[]> download(Integer id) throws IOException {
    Arquivo arquivo = this.dao.findBy("id", id);

    File file = new File(arquivo.getFileName());
		FileInputStream in = new FileInputStream(file);
    byte[] documentBody = IOUtils.toByteArray(in);

    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("application", "octet-stream"));
    header.set("Content-Disposition", "attachment; filename=" + arquivo.getFileName());
    header.setContentLength(documentBody.length);

    return new HttpEntity<byte[]>(documentBody, header);
  }

  public void remove(Integer id) throws IOException {
    Arquivo arquivo = this.dao.findBy("id", id);

    File file = new File(arquivo.getFileName());
    if(file.delete())
      this.dao.delete(arquivo);
  }

  public String fileName() {
    return UUID.randomUUID().toString();
  }
}
