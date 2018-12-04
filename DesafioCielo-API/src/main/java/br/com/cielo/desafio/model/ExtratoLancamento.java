package br.com.cielo.desafio.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class ExtratoLancamento implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -962690615722488907L;
	
	
	@JsonFormat(with=JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
	private List<ListaControleLancamento> listaControleLancamento;
	private Integer indice;
	private Integer TamanhoPagina;
	private Integer totalElements;

	
	/**
	 * @return the listaControleLancamento
	 */
	public List<ListaControleLancamento> getListaControleLancamento() {
		return listaControleLancamento;
	}
	/**
	 * @param listaControleLancamento the listaControleLancamento to set
	 */
	public void setListaControleLancamento(List<ListaControleLancamento> listaControleLancamento) {
		this.listaControleLancamento = listaControleLancamento;
	}
	/**
	 * @return the indice
	 */
	public Integer getIndice() {
		return indice;
	}
	/**
	 * @param indice the indice to set
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}
	/**
	 * @return the tamanhoPagina
	 */
	public Integer getTamanhoPagina() {
		return TamanhoPagina;
	}
	/**
	 * @param tamanhoPagina the tamanhoPagina to set
	 */
	public void setTamanhoPagina(Integer tamanhoPagina) {
		TamanhoPagina = tamanhoPagina;
	}
	/**
	 * @return the totalElements
	 */
	public Integer getTotalElements() {
		return totalElements;
	}
	/**
	 * @param totalElements the totalElements to set
	 */
	public void setTotalElements(Integer totalElements) {
		this.totalElements = totalElements;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((TamanhoPagina == null) ? 0 : TamanhoPagina.hashCode());
		result = prime * result + ((indice == null) ? 0 : indice.hashCode());
		result = prime * result + ((listaControleLancamento == null) ? 0 : listaControleLancamento.hashCode());
		result = prime * result + ((totalElements == null) ? 0 : totalElements.hashCode());
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
		ExtratoLancamento other = (ExtratoLancamento) obj;
		if (TamanhoPagina == null) {
			if (other.TamanhoPagina != null)
				return false;
		} else if (!TamanhoPagina.equals(other.TamanhoPagina))
			return false;
		if (indice == null) {
			if (other.indice != null)
				return false;
		} else if (!indice.equals(other.indice))
			return false;
		if (listaControleLancamento == null) {
			if (other.listaControleLancamento != null)
				return false;
		} else if (!listaControleLancamento.equals(other.listaControleLancamento))
			return false;
		if (totalElements == null) {
			if (other.totalElements != null)
				return false;
		} else if (!totalElements.equals(other.totalElements))
			return false;
		return true;
	}
	
	
	
}
