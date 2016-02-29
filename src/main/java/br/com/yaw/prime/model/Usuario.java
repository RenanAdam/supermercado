package br.com.yaw.prime.model;
// Generated Nov 4, 2015 3:58:18 PM by Hibernate Tools 4.0.0

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "usuario")
public class Usuario implements AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String usuario;
	private String senha;

	public Usuario() {
	}

	public Usuario(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	

	@Id
	@GeneratedValue(strategy = IDENTITY)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


}
