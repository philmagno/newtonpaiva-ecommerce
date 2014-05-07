package br.newton.ecommerce.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.newton.ecommerce.entity.CartaoCredito;
import br.newton.ecommerce.entity.Endereco;
import br.newton.ecommerce.entity.Pedido;
import br.newton.ecommerce.mocks.CorreiosServices;
import br.newton.ecommerce.mocks.CorreiosServicesMock;
import br.newton.ecommerce.mocks.PayPalServices;
import br.newton.ecommerce.mocks.PayPalServicesMock;

@ManagedBean(name="pagamentoBean")
@ViewScoped
public class PagamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private CartaoCredito cartaoCredito;
	private Endereco endereco;
	private String frete;

	@ManagedProperty("#{carrinhoBean}")
	private CarrinhoBean carrinhoBean;
	
	@PostConstruct
	public void init() {		
		
		if(cartaoCredito == null)
			cartaoCredito = new CartaoCredito();
		
		if(endereco == null)
			endereco = new Endereco();
	}

	public String realizarPagamento() { 
		PayPalServices payPalBusiness = new PayPalServicesMock();
		Pedido pedido = carrinhoBean.getPedidoCarrinho();
		boolean pagamento = payPalBusiness.iniciarPagamento(cartaoCredito, carrinhoBean.getUsuario(), carrinhoBean.getPedidoCarrinho());
		
		if(pagamento){
			this.resetItens();
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
		CorreiosServices correiosServices = new CorreiosServicesMock();
		double valorFrente = correiosServices.calcularFrete(endereco.getCep());
		carrinhoBean.getPedidoCarrinho().setValorFrete(valorFrente);
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