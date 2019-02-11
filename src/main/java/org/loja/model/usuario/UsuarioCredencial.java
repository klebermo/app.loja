package org.loja.model.usuario;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import org.loja.model.credencial.Credencial;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;
import java.io.Serializable;

@Entity
public class UsuarioCredencial {
  @EmbeddedId
	private UsuarioCredencialId id;

  @ManyToOne
	@JoinColumn(name = "fk_usuario", insertable = false, updatable = false)
	private Usuario usuario;

  @ManyToOne
	@JoinColumn(name = "fk_credencial", insertable = false, updatable = false)
	private Credencial credencial;

  @Column
	private Date dataExpiracao;

  public UsuarioCredencial() {
    this.usuario = null;
		this.credencial = null;
		this.dataExpiracao = null;
  }

  public UsuarioCredencial(Usuario b, Credencial p, Date f) {
		// create primary key
		this.id = new UsuarioCredencialId(b.getId(), p.getId());

		// initialize attributes
		this.usuario = b;
		this.credencial = p;
		this.dataExpiracao = f;

		// update relationships to assure referential integrity
		p.getUsuario().add(this);
		b.getCredenciais().add(this);
	}

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Credencial getCredencial() {
    return credencial;
  }

  public void setCredencial(Credencial credencial) {
    this.credencial = credencial;
  }

  public Date getDataExpiracao() {
    return dataExpiracao;
  }

  public void setDataExpiracao(Date dataExpiracao) {
    this.dataExpiracao = dataExpiracao;
  }

  @Embeddable
  public static class UsuarioCredencialId implements Serializable {
    @Column(name = "fk_usuario")
    protected Integer usuarioId;

    @Column(name = "fk_credencial")
    protected Integer credencialId;

    public UsuarioCredencialId() {
      this.usuarioId = 0;
			this.credencialId = 0;
    }

    public UsuarioCredencialId(Integer usuarioId, Integer credencialId) {
			this.usuarioId = usuarioId;
			this.credencialId = credencialId;
		}

    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((usuarioId == null) ? 0 : usuarioId.hashCode());
			result = prime * result + ((credencialId == null) ? 0 : credencialId.hashCode());
			return result;
		}

    @Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;

			UsuarioCredencialId other = (UsuarioCredencialId) obj;

			if (usuarioId == null) {
				if (other.usuarioId != null)
					return false;
			} else if (!usuarioId.equals(other.usuarioId))
				return false;

			if (credencialId == null) {
				if (other.credencialId != null)
					return false;
			} else if (!credencialId.equals(other.credencialId))
				return false;

			return true;
    }
  }
}
