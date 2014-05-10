package br.newton.ecommerce.bean;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.StreamedContent;

import br.newton.ecommerce.util.RelatorioUtil;
import br.newton.ecommerce.util.UtilException;


@ManagedBean(name="relatorioBean")
@ViewScoped
public class RelatorioBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432853825463557913L;
	
	private StreamedContent produtosVendidos;
	private StreamedContent pedidosCancelados;
	private StreamedContent quantidadeReclamacoes;
	private StreamedContent produtosSemEstoque;
	private int tipoRelatorio;
	private Date dataInicial;
	private Date dataFinal;
	
	SimpleDateFormat formatacaoData = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("unchecked")
	public StreamedContent getProdutosVendidos() {	
		
		FacesContext context = FacesContext.getCurrentInstance();		

		String nomeRelatorioJasper = "Produtos_Mais_Vendidos";
		String nomeRelatorioSaida = "Produtos_Mais_Vendidos_Ecommerce-Newton";
		String caminho = context.getExternalContext().getRealPath("relatorios") + File.separator;
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		@SuppressWarnings("rawtypes")
		HashMap parametrosRelatorio = new HashMap();
		
		parametrosRelatorio.put("dataInicial", this.dataInicial);
		parametrosRelatorio.put("dataFinal", this.dataFinal);
		parametrosRelatorio.put("caminho", caminho);
		
		try {
			
			this.produtosVendidos = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);		
			
		}catch (UtilException e) {
			
			context.addMessage(null, new FacesMessage(e.getMessage()));
			System.out.println(e);
			System.out.println(e.getMessage());
			return null;
		}
		
		return this.produtosVendidos;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public StreamedContent getPedidosCancelados() {	
		
		FacesContext context = FacesContext.getCurrentInstance();		

		String nomeRelatorioJasper = "Pedidos_Cancelados";
		String nomeRelatorioSaida = "Pedidos_Cancelados_Ecommerce-Newton";
		String caminho = context.getExternalContext().getRealPath("relatorios") + File.separator;
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		@SuppressWarnings("rawtypes")
		HashMap parametrosRelatorio = new HashMap();
		
		parametrosRelatorio.put("dataInicial", this.dataInicial);
		parametrosRelatorio.put("dataFinal", this.dataFinal);
		parametrosRelatorio.put("caminho", caminho);
		
		try {
			
			this.pedidosCancelados = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);		
			
		}catch (UtilException e) {
			
			context.addMessage(null, new FacesMessage(e.getMessage()));
			System.out.println(e);
			System.out.println(e.getMessage());
			return null;
		}
		
		return this.pedidosCancelados;
		
	}
	
	@SuppressWarnings("unchecked")
	public StreamedContent getQuantidadeReclamacoes() {	
		
		FacesContext context = FacesContext.getCurrentInstance();		

		String nomeRelatorioJasper = "Reclamacoes";
		String nomeRelatorioSaida = "Reclamacoes_Ecommerce-Newton";
		String caminho = context.getExternalContext().getRealPath("relatorios") + File.separator;
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		@SuppressWarnings("rawtypes")
		HashMap parametrosRelatorio = new HashMap();
		
		parametrosRelatorio.put("dataInicial", this.dataInicial);
		parametrosRelatorio.put("dataFinal", this.dataFinal);
		parametrosRelatorio.put("caminho", caminho);
		
		try {
			
			this.quantidadeReclamacoes = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);		
			
		}catch (UtilException e) {
			
			context.addMessage(null, new FacesMessage(e.getMessage()));
			System.out.println(e);
			System.out.println(e.getMessage());
			return null;
		}
		
		return this.quantidadeReclamacoes;
		
	}
	
	@SuppressWarnings("unchecked")
	public StreamedContent getProdutosSemEstoque() {	
		
		FacesContext context = FacesContext.getCurrentInstance();		

		String nomeRelatorioJasper = "Produtos_Sem_Estoque";
		String nomeRelatorioSaida = "Produtos_Sem_Estoque_Ecommerce-Newton";
		String caminho = context.getExternalContext().getRealPath("relatorios") + File.separator;
		RelatorioUtil relatorioUtil = new RelatorioUtil();
		@SuppressWarnings("rawtypes")
		HashMap parametrosRelatorio = new HashMap();
		
		parametrosRelatorio.put("caminho", caminho);
		
		try {
			
			this.produtosSemEstoque = relatorioUtil.geraRelatorio(parametrosRelatorio, nomeRelatorioJasper, nomeRelatorioSaida, this.tipoRelatorio);		
			
		}catch (UtilException e) {
			
			context.addMessage(null, new FacesMessage(e.getMessage()));
			System.out.println(e);
			System.out.println(e.getMessage());
			return null;
		}
		
		return this.produtosSemEstoque;
		
	}
	
	public void handleDateSelectInicial(SelectEvent event) {
		this.dataInicial = (Date) event.getObject();
		//Add facesmessage
		}
	
	public void handleDateSelectFinal(SelectEvent event) {
		this.dataFinal = (Date) event.getObject();
		//Add facesmessage
		}

	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public SimpleDateFormat getFormatacaoData() {
		return formatacaoData;
	}

	public void setFormatacaoData(SimpleDateFormat formatacaoData) {
		this.formatacaoData = formatacaoData;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setProdutosVendidos(StreamedContent produtosVendidos) {
		this.produtosVendidos = produtosVendidos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result
				+ ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result
				+ ((formatacaoData == null) ? 0 : formatacaoData.hashCode());
		result = prime
				* result
				+ ((produtosVendidos == null) ? 0 : produtosVendidos.hashCode());
		result = prime * result + tipoRelatorio;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelatorioBean other = (RelatorioBean) obj;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (formatacaoData == null) {
			if (other.formatacaoData != null)
				return false;
		} else if (!formatacaoData.equals(other.formatacaoData))
			return false;
		if (produtosVendidos == null) {
			if (other.produtosVendidos != null)
				return false;
		} else if (!produtosVendidos.equals(other.produtosVendidos))
			return false;
		if (tipoRelatorio != other.tipoRelatorio)
			return false;
		return true;
	}
}
