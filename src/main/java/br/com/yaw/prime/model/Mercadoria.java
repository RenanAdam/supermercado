package br.com.yaw.prime.model;
// Generated Nov 4, 2015 3:58:18 PM by Hibernate Tools 4.0.0

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mercadoria generated by hbm2java
 */
@Entity
@Table(name = "mercadoria")
public class Mercadoria implements AbstractEntity {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long id_supermercado;
	private Long id_usuario;
	private String descricao;
	private String unidade;
	private int quantidade;
	private double preco;
	private Date data;

	public Mercadoria() {
		this.data = new Date();
		this.quantidade = 1;
	}

	public Mercadoria(Long supermercado, String descricao, int quantidade, double preco, Date data) {
		this.id_supermercado = supermercado;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
	}

	public Mercadoria(Long supermercado, Long usuario, String descricao, String unidade, int quantidade,
			double preco, Date data) {
		this.id_supermercado = supermercado;
		this.id_usuario = usuario;
		this.descricao = descricao;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.preco = preco;
		this.data = data;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getId_supermercado() {
		return this.id_supermercado;
	}

	public void setId_supermercado(Long supermercado) {
		this.id_supermercado = supermercado;
	}

	
	public Long getId_usuario() {
		return this.id_usuario;
	}

	public void setId_usuario(Long usuario) {
		this.id_usuario = usuario;
	}


	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}


	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	
	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
