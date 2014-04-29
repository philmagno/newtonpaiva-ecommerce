package br.newton.ecommerce.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 8690091216409291248L;

	@Id
	@GeneratedValue
	@Column(name = "idpedido")
	private int pedido;

	@Column(name = "data")
	private Date data;
	
	private String status;
	private double valorFrete;

	@ManyToOne
	@JoinColumn(name = "codigo")
	private Usuario usuario;

	@Transient
	private List<Produto> itensPedido = new ArrayList<Produto>();

	@JoinColumn(name = "fkendereco")
	private Endereco endereco;


	/**
	 * Retorna o total do Pedido;
	 * @return
	 */
	public double getValorTotal(){
		Double valorTotal = 0.0D;
		
		for(Produto produto : itensPedido)
			valorTotal = valorTotal + produto.getValorTotal();
		
		if(valorFrete > 0)
			valorTotal = valorTotal + valorFrete;
		
		return valorTotal;
	}
	
	public void addItensPedido(Produto produto) {
		
		if(this.itensPedido.contains(produto)){
			for(Produto item : itensPedido){
				if(item.equals(produto)){
					item.setQuantidade(item.getQuantidade() +1);
				}
			}
		}else{
			this.itensPedido.add(produto);
		}
	}
	
	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
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

	public double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Produto> getItensPedido() {
		return itensPedido;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((itensPedido == null) ? 0 : itensPedido.hashCode());
		result = prime * result + pedido;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorFrete);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		if (itensPedido == null) {
			if (other.itensPedido != null)
				return false;
		} else if (!itensPedido.equals(other.itensPedido))
			return false;
		if (pedido != other.pedido)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(valorFrete) != Double
				.doubleToLongBits(other.valorFrete))
			return false;
		return true;
	}
	
}
