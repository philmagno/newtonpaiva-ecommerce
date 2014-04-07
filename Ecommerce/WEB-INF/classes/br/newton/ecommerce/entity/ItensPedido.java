package br.newton.ecommerce.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItensPedido implements Serializable, Comparable<ItensPedido> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7186371343266188598L;
	
	
    @ManyToOne
    @JoinColumn(name="cod_produto")
    private Produto produto;
	
	private Integer quantidade;
	private Float preco_unitario;
	private Float valor_total;
	
	  public ItensPedido(Produto produto) {
		    this.produto = produto;
		  }

		  public ItensPedido(Produto produto, Integer quantidade) {
		    this.produto = produto;
		    this.preco_unitario = produto.getPreco();
		    this.quantidade = quantidade;
		    calcularTotal();
		  }

		  public void calcularTotal() {
		    valor_total = preco_unitario * quantidade;
		  }

		  public void atualizarQuantidade(Integer novaQuantidade) {
		    this.quantidade = novaQuantidade;
		    calcularTotal();
		  }

	
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Float getPreco_unitario() {
		return preco_unitario;
	}
	public void setPreco_unitario(Float preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
	public Float getValor_total() {
		return valor_total;
	}
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((preco_unitario == null) ? 0 : preco_unitario.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
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
		ItensPedido other = (ItensPedido) obj;
		if (preco_unitario == null) {
			if (other.preco_unitario != null)
				return false;
		} else if (!preco_unitario.equals(other.preco_unitario))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valor_total == null) {
			if (other.valor_total != null)
				return false;
		} else if (!valor_total.equals(other.valor_total))
			return false;
		return true;
	}
	
	@Override public int compareTo(ItensPedido o) { 
		
		return produto.getNome().compareTo(o.getProduto().getNome()); 
		
	}


		
}
