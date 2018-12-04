package br.com.cielo.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class ListaControleLancamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7849150093742596187L;
	
	private LancamentoContaCorrenteCliente lancamentoContaCorrenteCliente;
	private String dataEfetivaLancamento;
	private String dataLancamentoContaCorrenteCliente;
	private Integer numeroEvento;
	private String descricaoGrupoPagamento;
	private String nomeBanco;
	private BigDecimal valorLancamentoRemessa;
	
	public LancamentoContaCorrenteCliente getLancamentoContaCorrenteCliente() {
		return lancamentoContaCorrenteCliente;
	}
	public void setLancamentoContaCorrenteCliente(LancamentoContaCorrenteCliente lancamentoContaCorrenteCliente) {
		this.lancamentoContaCorrenteCliente = lancamentoContaCorrenteCliente;
	}
	public String getDataEfetivaLancamento() {
		return dataEfetivaLancamento;
	}
	public void setDataEfetivaLancamento(String dataEfetivaLancamento) {
		this.dataEfetivaLancamento = dataEfetivaLancamento;
	}
	public String getDataLancamentoContaCorrenteCliente() {
		return dataLancamentoContaCorrenteCliente;
	}
	public void setDataLancamentoContaCorrenteCliente(String dataLancamentoContaCorrenteCliente) {
		this.dataLancamentoContaCorrenteCliente = dataLancamentoContaCorrenteCliente;
	}
	public Integer getNumeroEvento() {
		return numeroEvento;
	}
	public void setNumeroEvento(Integer numeroEvento) {
		this.numeroEvento = numeroEvento;
	}
	public String getDescricaoGrupoPagamento() {
		return descricaoGrupoPagamento;
	}
	public void setDescricaoGrupoPagamento(String descricaoGrupoPagamento) {
		this.descricaoGrupoPagamento = descricaoGrupoPagamento;
	}
	public String getNomeBanco() {
		return nomeBanco;
	}
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	public BigDecimal getValorLancamentoRemessa() {
		return valorLancamentoRemessa;
	}
	public void setValorLancamentoRemessa(BigDecimal valorLancamentoRemessa) {
		this.valorLancamentoRemessa = valorLancamentoRemessa;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEfetivaLancamento == null) ? 0 : dataEfetivaLancamento.hashCode());
		result = prime * result
				+ ((dataLancamentoContaCorrenteCliente == null) ? 0 : dataLancamentoContaCorrenteCliente.hashCode());
		result = prime * result + ((descricaoGrupoPagamento == null) ? 0 : descricaoGrupoPagamento.hashCode());
		result = prime * result
				+ ((lancamentoContaCorrenteCliente == null) ? 0 : lancamentoContaCorrenteCliente.hashCode());
		result = prime * result + ((nomeBanco == null) ? 0 : nomeBanco.hashCode());
		result = prime * result + ((numeroEvento == null) ? 0 : numeroEvento.hashCode());
		result = prime * result + ((valorLancamentoRemessa == null) ? 0 : valorLancamentoRemessa.hashCode());
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
		ListaControleLancamento other = (ListaControleLancamento) obj;
		if (dataEfetivaLancamento == null) {
			if (other.dataEfetivaLancamento != null)
				return false;
		} else if (!dataEfetivaLancamento.equals(other.dataEfetivaLancamento))
			return false;
		if (dataLancamentoContaCorrenteCliente == null) {
			if (other.dataLancamentoContaCorrenteCliente != null)
				return false;
		} else if (!dataLancamentoContaCorrenteCliente.equals(other.dataLancamentoContaCorrenteCliente))
			return false;
		if (descricaoGrupoPagamento == null) {
			if (other.descricaoGrupoPagamento != null)
				return false;
		} else if (!descricaoGrupoPagamento.equals(other.descricaoGrupoPagamento))
			return false;
		if (lancamentoContaCorrenteCliente == null) {
			if (other.lancamentoContaCorrenteCliente != null)
				return false;
		} else if (!lancamentoContaCorrenteCliente.equals(other.lancamentoContaCorrenteCliente))
			return false;
		if (nomeBanco == null) {
			if (other.nomeBanco != null)
				return false;
		} else if (!nomeBanco.equals(other.nomeBanco))
			return false;
		if (numeroEvento == null) {
			if (other.numeroEvento != null)
				return false;
		} else if (!numeroEvento.equals(other.numeroEvento))
			return false;
		if (valorLancamentoRemessa == null) {
			if (other.valorLancamentoRemessa != null)
				return false;
		} else if (!valorLancamentoRemessa.equals(other.valorLancamentoRemessa))
			return false;
		return true;
	}
	
	
	
	
	
}
