package br.newton.ecommerce.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.newton.ecommerce.entity.Usuario;
import br.newton.ecommerce.util.JPAUtil;

public class UsuarioDAOJpa implements UsuarioDAO {

	private EntityManager entityManager;

	public UsuarioDAOJpa() {
		this.entityManager = JPAUtil.getEntityManager();
	}
	
	@Override
	public void salvar(Usuario usuario) {
		if (!this.entityManager.isOpen())
			this.entityManager.getTransaction().begin();
		
		this.entityManager.persist(usuario);
		this.entityManager.flush();
	}

	@Override
	public void atualizar(Usuario usuario) {
		this.entityManager.merge(usuario);
	}

	@Override
	public void excluir(Usuario usuario) {
		this.entityManager.remove(usuario);
	}

	@Override
	public Usuario carregar(Integer codigo) {
		return null;
	}

	@Override
	public Usuario buscarPorLogin(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
