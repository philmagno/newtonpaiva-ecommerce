package br.newton.ecommerce.dao;

import org.hibernate.Session;

import br.newton.ecommerce.util.HibernateUtil;

public class AbstractDAO{
	private Session session;
	
	public AbstractDAO(){
		if(session == null)
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}
}
