package org.loja.model.arquivo;

import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.commons.io.IOUtils;
import java.util.UUID;
import java.util.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class ArquivoService extends org.loja.model.Service<Arquivo> {
  //private String file_path = System.getProperty("user.home")+File.separator+".app"+File.separator+"Uploads";

  private String file_path = new File("").getAbsolutePath() + File.separator + ".app" + File.separator + "Uploads";

  public ArquivoService() {
    super(Arquivo.class);
  }

  public Integer upload(String name, String bytes, String type) throws Exception {
    String file_name = file_path + File.separator + name + "_v1." + type;

    File file = new File(file_name);
		if(!file.exists())
			if(file.getParentFile().mkdirs())
				file.createNewFile();

    FileOutputStream out = new FileOutputStream(file);
    byte[] bytes_final = bytes.split(",")[1].getBytes();
    out.write(Base64.getDecoder().decode(bytes_final));

    Arquivo arquivo = new Arquivo();
    arquivo.setFileName(file_name);
    arquivo.setType(type);
    this.dao.insert(arquivo);

    return arquivo.getId();
  }

  public HttpEntity<byte[]> download(Integer arquivo_id) throws Exception {
    Arquivo arquivo = this.dao.findBy("id", arquivo_id);

    File file = new File(arquivo.getFileName());
		FileInputStream in = new FileInputStream(file);
    byte[] documentBody = IOUtils.toByteArray(in);

    HttpHeaders header = new HttpHeaders();
    header.setContentType(new MediaType("application", "octet-stream"));
    header.set("Content-Disposition", "attachment; filename=" + downloadName(arquivo.getFileName()));
    header.setContentLength(documentBody.length);

    return new HttpEntity<byte[]>(documentBody, header);
  }

  public void edit(Integer id, String name, String bytes, String type) throws Exception {
    Arquivo arquivo = (Arquivo) this.dao.findBy("id", id);
    arquivo.setVersion(arquivo.getVersion() + 1);
    arquivo.setFileName(file_path + File.separator + name + "_v" + arquivo.getVersion().toString() + "." + type);
    this.dao.update(arquivo);

    File file = new File(arquivo.getFileName());
		if(!file.exists())
			if(file.getParentFile().mkdirs())
				file.createNewFile();

    FileOutputStream out = new FileOutputStream(file);
    byte[] bytes_final = bytes.split(",")[1].getBytes();
    out.write(Base64.getDecoder().decode(bytes_final));
  }

  public void remove(Integer id) throws Exception {
    Arquivo arquivo = (Arquivo) this.dao.findBy("id", id);

    File file = new File(arquivo.getFileName());
    if(file.delete())
      this.dao.delete(arquivo);
  }

  public String downloadName(String name) {
    java.util.StringTokenizer st = new java.util.StringTokenizer(name, File.separator);
    String token = "";
    while (st.hasMoreTokens())
        token = st.nextToken();
    return token;
  }
}
