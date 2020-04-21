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
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.spi.MetadataImplementor;
import java.util.EnumSet;
import org.hibernate.tool.schema.TargetType;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.beans.factory.config.BeanDefinition;
import javax.persistence.Entity;

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

  String driverClassName = "org.postgresql.Driver";

  String dialect = "org.hibernate.dialect.PostgreSQLDialect";

  String url_prefix = "jdbc:postgresql://";

  String url_suffix = "appdata";

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

      String str = "spring.datasource.driverClassName="+driverClassName+"\n";
      str = str + "spring.datasource.url="+url_prefix+server+"/"+url_suffix+"\n";
      str = str + "spring.datasource.username="+user+"\n";
      str = str + "spring.datasource.password="+pass+"\n";
      //str = str + "spring.datasource.continue-on-error=true\n";

      str = str + "spring.jpa.database-platform="+dialect+"\n";
      str = str + "spring.jpa.dialect="+dialect+"\n";
      str = str + "spring.jpa.show-sql=false\n";
      //str = str + "spring.jpa.generate-ddl=false\n";

      byte[] strToBytes = str.getBytes();
      outputStream.write(strToBytes);
      outputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void criarBanco(String server, String user, String pass) throws Exception {
    Class.forName(driverClassName);
    String url = url_prefix+server+"/postgres";
		Connection conn = DriverManager.getConnection(url, user, pass);
		Statement stmt = conn.createStatement();
    ResultSet rs = stmt.executeQuery("SELECT count(*) FROM pg_catalog.pg_database WHERE datname = '"+url_suffix+"'");
    rs.next();
		int counter  = rs.getInt(1);
    if(counter > 0) {
      rs.close();
			stmt.close();
			conn.close();
    } else {
      stmt.executeUpdate("CREATE DATABASE "+url_suffix+" WITH OWNER "+user);
      rs.close();
			stmt.close();
			conn.close();
    }
  }

  public void criarTabelas(String server, String user, String pass) throws Exception {
    Connection conn = DriverManager.getConnection(url_prefix+server+"/"+url_suffix, user, pass);
    StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
    .applySetting("hibernate.hbm2ddl.auto", "create")
    .applySetting("hibernate.dialect", dialect)
    .applySetting("hibernate.id.new_generator_mappings", "true")
    .applySetting("javax.persistence.schema-generation-connection", conn)
    .build();

    MetadataSources sources = new MetadataSources(standardRegistry);
    for(Class<?> entity : lista_entidades())
      sources.addAnnotatedClass(entity);

    MetadataImplementor metadata = (MetadataImplementor) sources.getMetadataBuilder().build();

    SchemaExport export = new SchemaExport();
    export.create(EnumSet.of(TargetType.DATABASE), metadata);
    conn.close();
  }

  private List<Class<?>> lista_entidades() throws Exception {
      List<Class<?>> lista = new ArrayList<Class<?>>();

      ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
      scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
      for (BeanDefinition bd : scanner.findCandidateComponents("org.loja.model"))
        lista.add(Class.forName(bd.getBeanClassName()));

      System.out.println("lista: "+lista);
      return lista;
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
    clienteDao.insert(novo_cliente);

    novo_cliente.setUsuario(novo);
    clienteDao.update(novo_cliente);
  }
}
