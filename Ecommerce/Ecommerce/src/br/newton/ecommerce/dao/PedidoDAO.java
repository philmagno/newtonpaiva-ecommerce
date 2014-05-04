package br.newton.ecommerce.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.util.JPAUtil;

public class PedidoDAO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public PedidoDAO(){
		this.entityManager = JPAUtil.getEntityManager();
	}
	
	public void salvar(Pedido pedido){
		this.entityManager.persist(pedido);
	}

}