package br.newton.ecommerce.dao;


import org.hibernate.Session;

import br.newton.ecommerce.util.HibernateUtil;


public class DAOFactory {
	
	public static UsuarioDAO criarUsuarioDAO(){
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
		
	}
	
	public static Session criarConexao(){
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
}
