package br.newton.ecommerce.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Produto;
import br.newton.ecommerce.entity.Usuario;

@Named("carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {
	private static final long serialVersionUID = 7260399016080400950L;

	private Pedido pedidoCarrinho;
	private Usuario usuario;
	private Produto produto;

	@PostConstruct
	public void init() {
		produto = new Produto();
		pedidoCarrinho = new Pedido();
	}
		
	public String adicionarItem(Produto produto) {
			produto.setQuantidade(1);
			pedidoCarrinho.addItensPedido(produto);
			return "carrinho";
	}

	public String removerItem() {
		return "carrinho";
	}

	public String continuarComprando() {
		return "listaProduto";
	}

	public String finalizarPedido() {
		return "checkout?#collapseOne";
	}

	public void removerProduto(){
		
	}
	public Pedido getPedidoCarrinho() {
		return pedidoCarrinho;
	}

	public void setPedidoCarrinho(Pedido pedidoCarrinho) {
		this.pedidoCarrinho = pedidoCarrinho;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
