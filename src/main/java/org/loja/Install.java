package org.loja;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import org.loja.model.usuario.Usuario;
import org.loja.model.usuario.UsuarioDao;
import org.loja.model.cliente.Cliente;
import org.loja.model.cliente.ClienteDao;
import org.loja.model.credencial.Credencial;
import org.loja.model.credencial.CredencialDao;
import org.loja.model.autorizacao.Autorizacao;
import org.loja.model.autorizacao.AutorizacaoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import java.util.ArrayList;
import java.util.Properties;

@Service
public class Install {
  @Autowired
  private UsuarioDao usuarioDao;

  @Autowired
  private ClienteDao clienteDao;

  @Autowired
  private CredencialDao credencialDao;

  @Autowired
  private AutorizacaoDao autorizacaoDao;

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

  public void processaInstalacao(String server, String user, String pass, String admin_user, String admin_pass, String admin_nome, String admin_sobrnome, String admin_email) throws Exception {
    criaArquivos(server, user, pass);
    criarBanco(server, user, pass);
    criarTabelas(server, user, pass);
    preencherDadosIniciais(server, user, pass);
    criarUsuario(admin_user, admin_pass, admin_nome, admin_sobrnome, admin_email);
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

      String str = "spring.datasource.driverClassName=org.postgresql.Driver\n";
      str = str + "spring.datasource.url=jdbc:postgresql://"+server+"/appdata\n";
      str = str + "spring.datasource.username="+user+"\n";
      str = str + "spring.datasource.password="+pass+"\n";
      str = str + "spring.datasource.continue-on-error=true";

      str = str + "spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect\n";
      str = str + "spring.jpa.dialect=org.hibernate.dialect.PostgreSQLDialect\n";
      str = str + "spring.jpa.show-sql=false\n";
      str = str + "spring.jpa.generate-ddl=false\n";

      byte[] strToBytes = str.getBytes();
      outputStream.write(strToBytes);
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void criarBanco(String server, String user, String pass) throws Exception {
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://"+server+"/postgres";
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT count(*) FROM pg_catalog.pg_database WHERE datname = 'appdata'");
    rs.next();
		int counter  = rs.getInt(1);
    if(counter > 0) {
      rs.close();
			stmt.close();
			conn.close();
    } else {
      stmt.executeUpdate("CREATE DATABASE appdata WITH OWNER "+user);
      rs.close();
			stmt.close();
			conn.close();
    }
  }

  public void criarTabelas(String server, String user, String pass) throws Exception {
    Configuration config = new Configuration();

    Properties props = new Properties();
    FileInputStream inputStream = new FileInputStream( file_name );
    props.load(inputStream);
    inputStream.close();
    config.setProperties(props);

    Connection conn = DriverManager.getConnection(server, user, pass);
    SchemaExport schema = new SchemaExport();
    //
    conn.close();
  }

  public void preencherDadosIniciais(String server, String user, String pass) {
    String [] credenciais = {"usuario", "categoria", "produto", "credencial", "autorizacao", "imagem", "arquivo", "pedido", "pagina", "cliente"};
    for(int i=0; i<credenciais.length; i++) {
      Credencial c = new Credencial(credenciais[i]);
      credencialDao.insert(c);
      String [] actions = {"cadastra", "atualiza", "remove", "consulta"};
      for(int j=0; j<actions.length; j++) {
        Autorizacao a = new Autorizacao(actions[j]+"_"+credenciais[i]);
        autorizacaoDao.insert(a);
        c.getAutorizacoes().add(a);
      }
      credencialDao.update(c);
    }
    credencialDao.insert(new Credencial("admin"));
    autorizacaoDao.insert(new Autorizacao("admin"));
    Credencial c1 = credencialDao.findBy("nome", "admin");
    c1.getAutorizacoes().add(autorizacaoDao.findBy("nome", "admin"));
    credencialDao.update(c1);

    credencialDao.insert(new Credencial("web"));
    autorizacaoDao.insert(new Autorizacao("web"));
    Credencial c2 = credencialDao.findBy("nome", "web");
    c2.getAutorizacoes().add(autorizacaoDao.findBy("nome", "web"));
    credencialDao.update(c2);
  }

  public void criarUsuario(String user, String pass, String nome, String sobrenome, String email) {
    Usuario novo = new Usuario();
    novo.setUsername(user);
    novo.setPassword(pass);
    novo.setFirstName(nome);
    novo.setLastName(sobrenome);
    novo.setEmail(email);
    novo.setEnabled(true);
    novo.setLocked(false);
    novo.setCredenciais(new ArrayList<Credencial>());
    novo.getCredenciais().add( credencialDao.findBy("nome", "admin") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "usuario") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "categoria") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "produto") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "credencial") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "autorizacao") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "imagem") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "arquivo") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "pedido") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "pagina") );
    novo.getCredenciais().add( credencialDao.findBy("nome", "cliente") );
    usuarioDao.insert(novo);
    Cliente novo_cliente = new Cliente();
    novo_cliente.setUsuario(novo);
    clienteDao.insert(novo_cliente);
  }
}
