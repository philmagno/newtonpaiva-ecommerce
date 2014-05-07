package br.newton.ecommerce.mocks;

import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Usuario;

import com.paypal.core.rest.PayPalRESTException;

/**
 * 
 * @author philippe
 *
 */
public class PayPalServicesMock implements PayPalServices {

	@Override
	public void pagamento(CartaoCredito cartaoCredito)
			throws PayPalRESTException {

		if (cartaoCredito == null 
				|| cartaoCredito != null && cartaoCredito.getNumber().length() != 16
				|| cartaoCredito != null && cartaoCredito.getName() == null
				|| cartaoCredito != null && cartaoCredito.getName().trim().isEmpty())
			throw new PayPalRESTException("Erro realizar o pagamento");
	}

	@Override
	public boolean iniciarPagamento(CartaoCredito cartaoCredito,
			Usuario usuario, Pedido pedidoCarrinho) {
		
		try {
			pagamento(cartaoCredito);
			
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}