package br.newton.ecommerce.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "produtosBean")
@RequestScoped
public class ProdutosBean {
	String nome = "testes";

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
