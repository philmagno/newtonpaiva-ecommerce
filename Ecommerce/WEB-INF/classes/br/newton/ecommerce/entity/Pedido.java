package br.newton.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8690091216409291248L;
	
	@Id
	@GeneratedValue
	@Column(name="cod_pedido")
	private Integer pedido;
	
	private Date data;
	private String status;
	private Float valor_total;
	
	@ManyToOne
	@JoinColumn(name="codigo")
	private Usuario usuario;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "itens_pedido", joinColumns = @JoinColumn(name = "cod_pedido"))
	private Set<ItensPedido> itens = new HashSet<ItensPedido>();
	
	  public List<ItensPedido> getItensOrdenadosEmLista() {
		    return new ArrayList<ItensPedido>(getItens());
		  }

		  public void adicionarItem(Produto produto, Integer quantidade) {		
			  
			  ItensPedido itemExistente = new ItensPedido(produto);			  
		      itemExistente = getItem(produto);		      
		   
		    if (itemExistente != null) {
		      //atualizarQuantidade(produto, itemExistente.getQuantidade() + quantidade);
		    	atualizarQuantidade(produto, quantidade);
		    }
		    else {
		      getItens().add(new ItensPedido(produto, quantidade));
		      calcularTotal();
		    }
			  
			/*  getItens().add(new ItensPedido(produto, quantidade));
		      calcularTotal();*/
		  }

		  public void removerItem(Produto produto) {
			  
			  ItensPedido itemExistente = new ItensPedido(produto);			  
		      itemExistente = getItem(produto);	
		      getItens().remove(itemExistente);	  
		      calcularTotal();
		  }

		  public ItensPedido getItem(Produto produto) {
		    ItensPedido itemAProcurar = new ItensPedido(produto);		    
		    for (ItensPedido item : getItens()) {
		      if (item.getProduto().getNome().equals(itemAProcurar.getProduto().getNome())) {

		    	  return item;
		    	  
		      }
		    }
		    return null;
		  }

		  public void atualizarQuantidade(Produto produto, Integer novaQuantidade) {
		    ItensPedido item = getItem(produto);
		    if (item == null) { throw new IllegalArgumentException(
		        "Item nao encontrado para produto " + produto); }
		    item.atualizarQuantidade(novaQuantidade);
		    calcularTotal();
		  }

		  public void calcularTotal() {
		    valor_total = 0F;
		    for (ItensPedido item : getItens()) {
		      valor_total += item.getValor_total();
		    }
		  }

	public Integer getPedido() {
		return pedido;
	}

	public void setPedido(Integer pedido) {
		this.pedido = pedido;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Float getValor_total() {
		return valor_total;
	}

	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<ItensPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItensPedido> itens) {
		this.itens = itens;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result
				+ ((valor_total == null) ? 0 : valor_total.hashCode());
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
		Pedido other = (Pedido) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor_total == null) {
			if (other.valor_total != null)
				return false;
		} else if (!valor_total.equals(other.valor_total))
			return false;
		return true;
	}
}
