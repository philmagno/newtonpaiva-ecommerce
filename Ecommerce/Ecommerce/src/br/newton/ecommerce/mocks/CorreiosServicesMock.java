package br.newton.ecommerce.mocks;

import javax.enterprise.inject.Default;

/**
 * Implementação do mock para simulação dos webservices do correio.
 * 
 * @author philippe
 *
 */
@Default
public class CorreiosServicesMock implements CorreiosServices {

	@Override
	public double calcularFrete(String cep) {
		double retorno = 0;
		if(cep != null && cep.length() == 8)
			retorno = (int) (Math.random() * 100.0);
	
		return retorno;
	}

}
