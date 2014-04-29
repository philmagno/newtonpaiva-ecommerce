package br.newton.ecommerce.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.newton.ecommerce.business.ProdutoBusiness;
import br.newton.ecommerce.entity.Produto;
import br.newton.ecommerce.mocks.PayPalServicesProducao;

/**
 * ManagedBean responsável pela tela com a lista de produtos do site.
 * 
 * @author philippe
 * 
 */
@Named("produtoBean")
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
	
	@Inject
	private PayPalServicesProducao payPalProducao; 

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

	/*
	 * Getters e setters estão sendo colocados na parte de baixo. Apenas por uma
	 * questão de organização.
	 */

	public int getTotalProdutos() {
		return totalProdutos;
	}

	public void setTotalProdutos(int totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
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

	public ProdutoBusiness getProdutoBusiness() {
		return produtoBusiness;
	}

	public void setProdutoBusiness(ProdutoBusiness produtoBusiness) {
		this.produtoBusiness = produtoBusiness;
	}

	public String getFiltroProduto() {
		return filtroProduto;
	}

	public void setFiltroProduto(String filtroProduto) {
		this.filtroProduto = filtroProduto;
	}

}
