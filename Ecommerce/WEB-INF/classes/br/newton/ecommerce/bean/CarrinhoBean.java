package br.newton.ecommerce.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.newton.ecommerce.entity.ItensPedido;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Produto;
import br.newton.ecommerce.entity.Usuario;

@ManagedBean(name="carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7260399016080400950L;
	
	private Pedido pedidoCarrinho;
	private Usuario usuario;
	private Produto produtoRemover;
	private Produto produto = new Produto();	
	private Integer quantidade;

	
	@PostConstruct
	public void init(){
		
		pedidoCarrinho = new Pedido();
		
	}
	
	  public String adicionarItem() {
		  
		  FacesContext context = FacesContext.getCurrentInstance();
		  
		  if(this.produto.getQuantidade() == null || this.produto.getQuantidade() == 0){
			  
			  this.produto.setQuantidade(this.produto.getQuantidade() + 1);
			  
		  }
		  
		  
		  if(this.produto.getQuantidade() == null){
			  
			  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao Adicionar Produto", "Favor inserir a quantidade desejada"));  		  
			  return null;
			  
		  }else{
			  
			  pedidoCarrinho.adicionarItem(this.produto, this.produto.getQuantidade());
			  
			  context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Produto adicionado ao carrinho", "Foram inseridas "+this.produto.getQuantidade()+" caixas de "+this.produto.getNome()+" ao seu pedido" ));
			  return "carrinho";
			  
		  }
		  
		   		   
		  
		  }
	  
	  

		  public String removerItem() {
		    pedidoCarrinho.removerItem(produtoRemover);
		    return "carrinho";
		  }
		  
		  public String continuarComprando(){
			  
			  return "listaProduto";
			  
		  }

		  public void atualizarQuantidadeItem(Produto produto, Integer novaQuantidade) {
			    pedidoCarrinho.atualizarQuantidade(produto, novaQuantidade);
			  }

		  public String fecharPedidoUsuarioExistente() {
		    return fecharPedido();
		  }
		 
		  private String fecharPedido() {
		   
		   return null;
			  
		  }

		  public void recalcularTotal(ItensPedido itemPedido) {
		    itemPedido.calcularTotal();
		    pedidoCarrinho.calcularTotal();
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

		public Produto getProdutoRemover() {
			return produtoRemover;
		}

		public void setProdutoRemover(Produto produtoRemover) {
			this.produtoRemover = produtoRemover;
		}

		public Produto getProduto() {
			return produto;
		}

		public void setProduto(Produto produto) {
			this.produto = produto;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		
		public Integer getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(Integer quantidade) {
			this.quantidade = quantidade;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime
					* result
					+ ((pedidoCarrinho == null) ? 0 : pedidoCarrinho.hashCode());
			result = prime * result
					+ ((produto == null) ? 0 : produto.hashCode());
			result = prime
					* result
					+ ((produtoRemover == null) ? 0 : produtoRemover.hashCode());
			result = prime * result
					+ ((quantidade == null) ? 0 : quantidade.hashCode());
			result = prime * result
					+ ((usuario == null) ? 0 : usuario.hashCode());
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
			CarrinhoBean other = (CarrinhoBean) obj;
			if (pedidoCarrinho == null) {
				if (other.pedidoCarrinho != null)
					return false;
			} else if (!pedidoCarrinho.equals(other.pedidoCarrinho))
				return false;
			if (produto == null) {
				if (other.produto != null)
					return false;
			} else if (!produto.equals(other.produto))
				return false;
			if (produtoRemover == null) {
				if (other.produtoRemover != null)
					return false;
			} else if (!produtoRemover.equals(other.produtoRemover))
				return false;
			if (quantidade == null) {
				if (other.quantidade != null)
					return false;
			} else if (!quantidade.equals(other.quantidade))
				return false;
			if (usuario == null) {
				if (other.usuario != null)
					return false;
			} else if (!usuario.equals(other.usuario))
				return false;
			return true;
		}
}
