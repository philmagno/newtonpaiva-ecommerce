package br.newton.ecommerce.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.util.JPAUtil;

public class PedidoDao implements Serializable{
	private static final long serialVersionUID = 1L;
	private EntityManager entityManager;
	
	public PedidoDao(){
		this.entityManager = JPAUtil.getEntityManager();
	}
	
	public void salvar(Pedido pedido, CartaoCredito cartaoCredito){
//		if(getSession() == null ||
//				getSession() != null && !getSession().isOpen()){
//			this.setSession(DAOFactory.criarConexao());
//		}
		
//		if(pedido.getPedido() != null && pedido.getPedido()  < 0 )
//			getSession().persist(pedido);
//		else
//			getSession().merge(pedido);
		
//		if(cartaoCredito.getId()  <= 0 )
//			getSession().persist(cartaoCredito);
//		else
//			getSession().merge(cartaoCredito);
		
	}

}
