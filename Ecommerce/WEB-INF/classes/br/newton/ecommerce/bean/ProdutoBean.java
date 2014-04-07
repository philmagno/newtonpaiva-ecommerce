package br.newton.ecommerce.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.newton.ecommerce.business.ProdutoBusiness;
import br.newton.ecommerce.entity.Produto;

/**
 * ManagedBean responsável pela tela com a lista de produtos do site.
 * 
 * @author philippe
 * 
 */
@ManagedBean
@RequestScoped
public class ProdutoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Produto> listaProdutos;

	private ProdutoBusiness produtoBusiness;
	private int totalProdutos = 1;
	int index = 0;
	int limit = 12;
	private String filtroProduto;

	public ProdutoBean() {
		produtoBusiness = new ProdutoBusiness();
		if (listaProdutos == null) {
			listaProdutos = produtoBusiness.findPaginated(index, 10);
		}
	}

	public void buscarPaginada() {
		buscaPaginada(null);
	}

	public void buscaPaginada(ActionEvent e) {
		List<Produto> produtos = produtoBusiness.findPaginated(index, limit);
		if (produtos.size() > 0) {
			this.setListaProdutos(produtos);
		}
	}

	public String pesquisaFiltrada() {
		List<Produto> produtos = produtoBusiness.filtredFind(filtroProduto);
		this.setListaProdutos(produtos);

		return "true";
	}

	public String pesquisaCategoria() {
		Map<String,String> params =  FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		 
		String categoria = params.get("categoria");
		List<Produto> produtos = produtoBusiness.filtroCategoria(categoria);
		this.setListaProdutos(produtos);

		return "true";
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}

	public ProdutoBusiness getProdutoBusiness() {
		return produtoBusiness;
	}

	public void setProdutoBusiness(ProdutoBusiness produtoBusiness) {
		this.produtoBusiness = produtoBusiness;
	}

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFiltroProduto() {
		return filtroProduto;
	}

	public void setFiltroProduto(String filtroProduto) {
		this.filtroProduto = filtroProduto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((filtroProduto == null) ? 0 : filtroProduto.hashCode());
		result = prime * result + index;
		result = prime * result + limit;
		result = prime * result
				+ ((listaProdutos == null) ? 0 : listaProdutos.hashCode());
		result = prime * result
				+ ((produtoBusiness == null) ? 0 : produtoBusiness.hashCode());
		result = prime * result + totalProdutos;
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
		ProdutoBean other = (ProdutoBean) obj;
		if (filtroProduto == null) {
			if (other.filtroProduto != null)
				return false;
		} else if (!filtroProduto.equals(other.filtroProduto))
			return false;
		if (index != other.index)
			return false;
		if (limit != other.limit)
			return false;
		if (listaProdutos == null) {
			if (other.listaProdutos != null)
				return false;
		} else if (!listaProdutos.equals(other.listaProdutos))
			return false;
		if (produtoBusiness == null) {
			if (other.produtoBusiness != null)
				return false;
		} else if (!produtoBusiness.equals(other.produtoBusiness))
			return false;
		if (totalProdutos != other.totalProdutos)
			return false;
		return true;
	}

	/*
	 * Getters e setters estão sendo colocados na parte de baixo. Apenas por uma
	 * questão de organização.
	 */

}
