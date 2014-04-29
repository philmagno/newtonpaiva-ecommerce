package br.newton.ecommerce.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named("produtosBean")
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
