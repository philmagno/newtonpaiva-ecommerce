package br.newton.ecommerce.mocks;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSSoap;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

public class CorreiosServicesProducao implements CorreiosServices {

	@Override
	public double calcularFrete(String cep) {
		System.out.println("iniciando calculo do frete");
		double retorno = 0;
		try {
			CalcPrecoPrazoWSSoap calcpreco = new CalcPrecoPrazoWSSoapProxy();

			CResultado calcPrecoPrazo = calcpreco.calcPrecoPrazo("", "",
					"40010", "31365250", cep, "3", 1, new BigDecimal(40),
					new BigDecimal(40), new BigDecimal(40), new BigDecimal(42),
					"N", new BigDecimal(100), "S");
			System.out.println(calcPrecoPrazo);

			System.out.println("Lista de serviços: "
					+ calcPrecoPrazo.getServicos());

			CServico servico = calcPrecoPrazo.getServicos()[0];
			retorno = Double.valueOf(servico.getValor().replace(',', '.'));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return retorno;
	}

}