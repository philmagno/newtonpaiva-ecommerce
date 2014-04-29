package br.newton.ecommerce.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.newton.ecommerce.entity.Produto;
import br.newton.ecommerce.util.JPAUtil;

public class ProdutoDAO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;

	public ProdutoDAO() {
		this.entityManager = JPAUtil.getEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findPaginated(int index, int max) {

		Query query = this.entityManager
				.createNativeQuery(
						"select * from produto order by idproduto limit :index , :max",
						Produto.class).setParameter("index", index)
				.setParameter("max", max);
		List<Produto> produtos = query.getResultList();
		for (Produto p : produtos) {
			System.out.println(p.getId());
		}

		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findAll() {

		Query query = this.entityManager.createNativeQuery(
				"select * from produto", Produto.class);
		List<Produto> produtos = query.getResultList();

		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filteredFind(String filtro) {
		Query query = this.entityManager.createNativeQuery(
				"select * from produto where nome_produto like :nome limit 12",
				Produto.class).setParameter("nome", filtro + "%");

		List<Produto> produtos = query.getResultList();

		return produtos;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> filtroCategoria(String categoria) {
		Query query = this.entityManager.createNativeQuery(
				"select * from produto where categoria = :categoria",
				Produto.class).setParameter("categoria", categoria);

		List<Produto> produtos = query.getResultList();

		return produtos;
	}

}