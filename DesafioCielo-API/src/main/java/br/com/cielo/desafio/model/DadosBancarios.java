package br.com.cielo.desafio.model;

import java.math.BigDecimal;

public class DadosBancarios {

    private Integer codigoBanco;
    private Integer numeroAgencia;
    private BigDecimal numeroContaCorrente;
	public Integer getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	public BigDecimal getNumeroContaCorrente() {
		return numeroContaCorrente;
	}
	public void setNumeroContaCorrente(BigDecimal numeroContaCorrente) {
		this.numeroContaCorrente = numeroContaCorrente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBanco == null) ? 0 : codigoBanco.hashCode());
		result = prime * result + ((numeroAgencia == null) ? 0 : numeroAgencia.hashCode());
		result = prime * result + ((numeroContaCorrente == null) ? 0 : numeroContaCorrente.hashCode());
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
		DadosBancarios other = (DadosBancarios) obj;
		if (codigoBanco == null) {
			if (other.codigoBanco != null)
				return false;
		} else if (!codigoBanco.equals(other.codigoBanco))
			return false;
		if (numeroAgencia == null) {
			if (other.numeroAgencia != null)
				return false;
		} else if (!numeroAgencia.equals(other.numeroAgencia))
			return false;
		if (numeroContaCorrente == null) {
			if (other.numeroContaCorrente != null)
				return false;
		} else if (!numeroContaCorrente.equals(other.numeroContaCorrente))
			return false;
		return true;
	}
	
	
	
}
