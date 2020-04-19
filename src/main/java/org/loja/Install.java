package org.loja;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;

@Service
public class Install {
  String dir_name = new File("").getAbsolutePath();

  String file_name = dir_name + File.separator + "application.properties";

  public Boolean estaInstalado() {
    Path dir, file;

    try {
			dir = Paths.get(dir_name);
			if(!Files.exists(dir))
				Files.createDirectories(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}

    try {
			file = Paths.get(file_name);
			if(Files.exists(file))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

    return false;
  }

  public void processaInstalacao(String server, String user, String pass, String admin_user, String admin_pass) {
    criaArquivos(server, user, pass);
    criarBanco(server, user, pass, "appdata");
    criarTabelas(server, user, pass, "appdata");
    criarUsuario(admin_user, admin_pass);
  }

  public void criaArquivos(String server, String user, String pass) {
    Path dir, file;

    try {
			dir = Paths.get(dir_name);
			if(!Files.exists(dir))
				Files.createDirectories(dir);
		} catch (Exception e) {
			e.printStackTrace();
		}

    try {
			file = Paths.get(file_name);
			if(!Files.exists(file))
				Files.createFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}

    try {
      FileOutputStream outputStream = new FileOutputStream(file_name);

      String str = "spring.datasource.driverClassName=org.hsqldb.jdbcDriver\n";
      str = str + "spring.datasource.url="+server+"/appdata?serverTimezone=UTC\n";
      str = str + "spring.datasource.username="+user+"\n";
      str = str + "spring.datasource.password="+pass+"\n";
      str = str + "spring.datasource.continue-on-error=true";

      str = str + "spring.jpa.database-platform=org.hibernate.dialect.HSQLDialect\n";
      str = str + "spring.jpa.dialect=org.hibernate.dialect.HSQLDialect\n";
      str = str + "spring.jpa.show-sql=false\n";
      str = str + "spring.jpa.generate-ddl=false\n";

      byte[] strToBytes = str.getBytes();
      outputStream.write(strToBytes);
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void criarBanco(String server, String user, String pass, String database) {
    //
  }

  public void criarTabelas(String server, String user, String pass, String database) {
    //
  }

  public void criarUsuario(String user, String pass) {
    //
  }
}
