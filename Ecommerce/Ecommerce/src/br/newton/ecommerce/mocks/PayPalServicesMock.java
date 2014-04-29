package br.newton.ecommerce.mocks;

import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Usuario;

import com.paypal.core.rest.PayPalRESTException;

public class PayPalServicesMock implements PayPalServices{

	@Override
	public void pagamento(CartaoCredito cartaoCredito)
			throws PayPalRESTException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean iniciarPagamento(CartaoCredito cartaoCredito,
			Usuario usuario, Pedido pedidoCarrinho) {
		// TODO Auto-generated method stub
		return false;
	}

}
