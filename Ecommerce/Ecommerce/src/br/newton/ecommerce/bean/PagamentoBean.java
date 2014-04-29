package br.newton.ecommerce.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.rmi.RemoteException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWSSoap;
import org.tempuri.CalcPrecoPrazoWSSoapProxy;

import br.newton.ecommerce.business.PayPalBusiness;
import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Endereco;
import br.newton.ecommerce.entity.Pedido;

@Named("pagamentoBean")
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private CartaoCredito cartaoCredito;
	private PayPalBusiness payPalBusiness;
	private Endereco endereco;
	private String frete;

	@ManagedProperty("#{carrinhoBean}")
	private CarrinhoBean carrinhoBean;

	@PostConstruct
	public void init() {
		payPalBusiness = new PayPalBusiness();
		cartaoCredito = new CartaoCredito();
		endereco = new Endereco();
	}

	public String realizarPagamento() {  
		Pedido pedido = carrinhoBean.getPedidoCarrinho();
//		pedido.setEndereco(endereco);
		boolean pagamento = payPalBusiness.iniciarPagamento(cartaoCredito, carrinhoBean.getUsuario(), carrinhoBean.getPedidoCarrinho());
		
		if(pagamento){
			//cria novos objetos para serem utilizados nos beans
			this.resetItens();
			carrinhoBean.removerItem();
			return "resumo" ;
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Numero do Cartão Inválido", "PrimeFaces makes no mistakes")); 
			return "";
		}
	}
	
	private void resetItens(){
		this.setCartaoCredito(new CartaoCredito());
		this.setEndereco(new Endereco());
		this.setFrete("0");
	}

	public void calcularFrete() {
		System.out.println("iniciando calculo do frete");
		
		 try {
			 CalcPrecoPrazoWSSoap calcpreco = new CalcPrecoPrazoWSSoapProxy();
			
			 CResultado calcPrecoPrazo = calcpreco.calcPrecoPrazo("", "",
					 "40010", "31365250", endereco.getCep(), "3", 1,
			 new BigDecimal(40), new BigDecimal(40), new BigDecimal(40),
			 new BigDecimal(42), "N", new BigDecimal(100), "S");
			 System.out.println(calcPrecoPrazo);
			
			 System.out.println("Lista de serviços: "+ calcPrecoPrazo.getServicos());
			
			 CServico servico = calcPrecoPrazo.getServicos()[0];
//			 carrinhoBean.getPedidoCarrinho().setValorfrete(Double.valueOf(servico.getValor().replace(',', '.')));

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public CartaoCredito getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(CartaoCredito cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public CarrinhoBean getCarrinhoBean() {
		return carrinhoBean;
	}

	public void setCarrinhoBean(CarrinhoBean carrinhoBean) {
		this.carrinhoBean = carrinhoBean;
	}

	public String getFrete() {
		return frete;
	}

	public void setFrete(String frete) {
		this.frete = frete;
	}

}
