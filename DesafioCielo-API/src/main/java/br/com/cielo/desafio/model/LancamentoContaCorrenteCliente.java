package br.com.cielo.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class LancamentoContaCorrenteCliente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9032262830301038064L;
	
	private BigDecimal numeroRemessaBanco;
	private String nomeSituacaoRemessa;
	private DadosDomicilioBancario dadosDomicilioBancario;
	private String nomeTipoOperacao;
	
	public BigDecimal getNumeroRemessaBanco() {
		return numeroRemessaBanco;
	}
	public void setNumeroRemessaBanco(BigDecimal numeroRemessaBanco) {
		this.numeroRemessaBanco = numeroRemessaBanco;
	}
	public String getNomeSituacaoRemessa() {
		return nomeSituacaoRemessa;
	}
	public void setNomeSituacaoRemessa(String nomeSituacaoRemessa) {
		this.nomeSituacaoRemessa = nomeSituacaoRemessa;
	}
	public DadosDomicilioBancario getDadosDomicilioBancario() {
		return dadosDomicilioBancario;
	}
	public void setDadosDomicilioBancario(DadosDomicilioBancario dadosDomicilioBancario) {
		this.dadosDomicilioBancario = dadosDomicilioBancario;
	}
	public String getNomeTipoOperacao() {
		return nomeTipoOperacao;
	}
	public void setNomeTipoOperacao(String nomeTipoOperacao) {
		this.nomeTipoOperacao = nomeTipoOperacao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dadosDomicilioBancario == null) ? 0 : dadosDomicilioBancario.hashCode());
		result = prime * result + ((nomeSituacaoRemessa == null) ? 0 : nomeSituacaoRemessa.hashCode());
		result = prime * result + ((nomeTipoOperacao == null) ? 0 : nomeTipoOperacao.hashCode());
		result = prime * result + ((numeroRemessaBanco == null) ? 0 : numeroRemessaBanco.hashCode());
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
		LancamentoContaCorrenteCliente other = (LancamentoContaCorrenteCliente) obj;
		if (dadosDomicilioBancario == null) {
			if (other.dadosDomicilioBancario != null)
				return false;
		} else if (!dadosDomicilioBancario.equals(other.dadosDomicilioBancario))
			return false;
		if (nomeSituacaoRemessa == null) {
			if (other.nomeSituacaoRemessa != null)
				return false;
		} else if (!nomeSituacaoRemessa.equals(other.nomeSituacaoRemessa))
			return false;
		if (nomeTipoOperacao == null) {
			if (other.nomeTipoOperacao != null)
				return false;
		} else if (!nomeTipoOperacao.equals(other.nomeTipoOperacao))
			return false;
		if (numeroRemessaBanco == null) {
			if (other.numeroRemessaBanco != null)
				return false;
		} else if (!numeroRemessaBanco.equals(other.numeroRemessaBanco))
			return false;
		return true;
	}
	
	

}
