package br.newton.ecommerce.business;

import java.util.List;

import br.newton.ecommerce.dao.ProdutoDAO;
import br.newton.ecommerce.entity.Produto;

/**
 * Implementação da interface ProdutoFacade. Responsável por realizar as
 * operações para a entidade Produto.
 * 
 * @author philippe
 * 
 */
public class ProdutoBusiness {

	private ProdutoDAO dao;

	public ProdutoBusiness() {
		dao = new ProdutoDAO();
	}

	// @Override
	// public void save(Produto produto) {
	// dao.save(produto);
	// }

	public List<Produto> findAll() {
		return dao.findAll();
	}

	public List<Produto> findPaginated(int index, int max) {
		return dao.findPaginated(index, max);
	}

	public List<Produto> filtredFind(String filtroProduto) {
		return dao.filteredFind(filtroProduto);
	}

	public List<Produto> filtroCategoria(String categoria) {
		return dao.filtroCategoria(categoria);
	}

}
