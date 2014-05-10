package br.newton.ecommerce.mocks;


/**
 * Implementação do mock para simulação dos webservices do correio.
 * 
 * @author philippe
 *
 */
public class CorreiosServicesMock implements CorreiosServices {

	@Override
	public double calcularFrete(String cep) {
		double retorno = 0;
		if(cep != null && cep.length() > 7){
			retorno = (int) (Math.random() * 100.0);
		}
	
		return retorno;
	}

}
