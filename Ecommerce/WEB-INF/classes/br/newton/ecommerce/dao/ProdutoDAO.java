package br.newton.ecommerce.dao;

import java.util.List;

import org.hibernate.Query;

import br.newton.ecommerce.entity.Produto;

public class ProdutoDAO extends AbstractDAO {

	public ProdutoDAO() {
		super();
	}

	@SuppressWarnings("unchecked")
	public List<Produto> findPaginated(int index, int max) {
		if(getSession() == null ||
				getSession() != null && !getSession().isOpen()){
			this.setSession(DAOFactory.criarConexao());
		}
		
		Query query = getSession()
				.createSQLQuery(
						"select * from produto order by idproduto limit :index , :max")
				.addEntity(Produto.class).setParameter("index", index)
				.setParameter("max", max);
		List<Produto> produtos = query.list();
		for (Produto p : produtos) {
			System.out.println(p.getId());
		}

		return produtos;
	}

	public List<Produto> findAll() {
		if(getSession() == null ||
				getSession() != null && !getSession().isOpen()){
			this.setSession(DAOFactory.criarConexao());
		}
		
		Query query = getSession().createSQLQuery("select * from produto")
				.addEntity(Produto.class);
		List<Produto> produtos = query.list();

		return produtos;
	}
	
	public List<Produto> filteredFind(String filtro) {
		if(getSession() == null ||
				getSession() != null && !getSession().isOpen()){
			this.setSession(DAOFactory.criarConexao());
		}
		
		Query query = getSession().createSQLQuery("select * from produto where nome_produto like :nome limit 12")
				.addEntity(Produto.class).setParameter("nome", filtro+"%");
		
		List<Produto> produtos = query.list();

		return produtos;
	}

	public List<Produto> filtroCategoria(String categoria) {
		if(getSession() == null ||
				getSession() != null && !getSession().isOpen()){
			this.setSession(DAOFactory.criarConexao());
		}
		
		Query query = getSession().createSQLQuery("select * from produto where categoria = :categoria")
				.addEntity(Produto.class).setParameter("categoria", categoria);
		
		List<Produto> produtos = query.list();

		return produtos;
	}

}
