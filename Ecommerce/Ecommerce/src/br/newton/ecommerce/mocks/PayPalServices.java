package br.newton.ecommerce.mocks;

import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Usuario;

import com.paypal.core.rest.PayPalRESTException;

public interface PayPalServices{
	
	void pagamento(CartaoCredito cartaoCredito) throws PayPalRESTException;
	public boolean iniciarPagamento(CartaoCredito cartaoCredito, Usuario usuario, Pedido pedidoCarrinho);

}
