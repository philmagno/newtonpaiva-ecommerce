package br.newton.ecommerce.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.newton.ecommerce.dao.PedidoDao;
import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.entity.Usuario;

import com.paypal.api.payments.Address;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.CreditCard;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.FundingInstrument;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;

public class PayPalBusiness implements Serializable {

	private static final long serialVersionUID = 1L;
	private PedidoDao pedidoDao;

	public PayPalBusiness() {
		pedidoDao = new PedidoDao();
	}

	public void pagamento(CartaoCredito cartaoCredito)
			throws PayPalRESTException {
		String clientID = "Ab281RDhJXMIgTHY-ve-HDOjfZquFVpzyiwg9iiAPItaAqWRDYbAvB_j1Zsv";
		String clientSecret = "EAZ7IhDirYuh3KR0mrApsZQEEkH5aHmbL6fWH_1hW7EBIgGCddMr5kFZTNj0";

		String accessToken = "";

		// Payment.initConfig(new
		// File("src/main/resources/sdk_config.properties"));
		accessToken = new OAuthTokenCredential(clientID, clientSecret)
				.getAccessToken();

		Address billingAddress = new Address();
		billingAddress.setCity("Johnstown");
		billingAddress.setCountryCode("US");
		billingAddress.setLine1("52 N Main ST");
		billingAddress.setPostalCode("43210");
		billingAddress.setState("OH");

		CreditCard creditCard = new CreditCard();
		creditCard.setBillingAddress(billingAddress);
		creditCard.setCvv2(cartaoCredito.getCvv2());
		creditCard.setExpireMonth(cartaoCredito.getExpireMonth());
		creditCard.setExpireYear(cartaoCredito.getExpireYear());
		creditCard.setFirstName(cartaoCredito.getName());
		creditCard.setLastName(cartaoCredito.getName());
		creditCard.setNumber(cartaoCredito.getNumber());
		creditCard.setType("visa");

		Details details = new Details();
		details.setShipping("1");
		details.setSubtotal("5");
		details.setTax("1");

		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal("7");
		amount.setDetails(details);

		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
				.setDescription("This is the payment transaction description.");

		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		FundingInstrument fundingInstrument = new FundingInstrument();
		fundingInstrument.setCreditCard(creditCard);

		List<FundingInstrument> fundingInstrumentList = new ArrayList<FundingInstrument>();
		fundingInstrumentList.add(fundingInstrument);

		Payer payer = new Payer();
		payer.setFundingInstruments(fundingInstrumentList);
		payer.setPaymentMethod("credit_card");

		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setTransactions(transactions);

		String accessToken2 = accessToken;
		APIContext apiContext = new APIContext(accessToken2);

		Payment createdPayment;

		createdPayment = payment.create(apiContext);
		System.out.println(Payment.getLastResponse());
		System.out.println("Created payment with id = "
				+ createdPayment.getId() + " and status = "
				+ createdPayment.getState());
	}

	public boolean iniciarPagamento(CartaoCredito cartaoCredito,
			Usuario usuario, Pedido pedidoCarrinho) {
		try {
			pedidoDao.salvar(pedidoCarrinho, cartaoCredito);
			pagamento(cartaoCredito);
			return true;
		} catch (PayPalRESTException e) {
			e.printStackTrace();
			return false;
		}
	}
}
