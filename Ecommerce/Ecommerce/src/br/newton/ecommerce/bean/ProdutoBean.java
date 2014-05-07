package br.newton.ecommerce.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
@ManagedBean(name="produtoBean")
@SessionScoped
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
	private Produto produtoSelecionado;

	@PostConstruct
	public void init() {
		produtoBusiness = new ProdutoBusiness();
		if (listaProdutos == null) {
			listaProdutos = produtoBusiness.findPaginated(index, 10);
		}
		
		if(produtoSelecionado == null)
				produtoSelecionado = new Produto();
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
	
	public String novoProduto(){
		this.produtoSelecionado = new Produto();
		
		return "cadProduto";
	}
	
	public String alterarProduto(Produto produto){
		this.produtoSelecionado = produto;
		
		return "cadProduto";
	}
	
	public String salvarProduto(){
		Produto produtoTMP;
		if(produtoSelecionado.getId() <= 0){
			produtoTMP = produtoBusiness.save(produtoSelecionado);
			listaProdutos.add(produtoTMP);
		}else{
			produtoTMP = produtoBusiness.save(produtoSelecionado);
		}
				
		return "crudProduto";
	}
	
	public String excluirProduto(Produto produto){
		listaProdutos.remove(produto);
		produtoBusiness.remove(produto);
		
		return "crudProduto";
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

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
	}
	
}