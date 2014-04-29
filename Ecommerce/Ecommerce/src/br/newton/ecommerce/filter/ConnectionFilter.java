package br.newton.ecommerce.filter;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import br.newton.ecommerce.util.JPAUtil;

@WebFilter
public class ConnectionFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		EntityManager entityManager = JPAUtil.getEntityManager();

		try {
			entityManager.getTransaction().begin();
			chain.doFilter(request, response);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if(entityManager.isOpen())
				entityManager.getTransaction().rollback();
		} finally {
			if(entityManager.isOpen())
				entityManager.close();
		}
	}
}