package br.com.cielo.desafio.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.model.ListaControleLancamento;


public interface ExtratoRepository {
	


	public boolean deleteByNumeroRemessa(BigDecimal num);

	public boolean updateByNumeroRemessa(ListaControleLancamento lancamento);

	public ListaControleLancamento saveLancamento(@Valid ListaControleLancamento lanc, BigDecimal remessa);

	public List<ListaControleLancamento> getLancamentosByRemessa(BigDecimal numeroRemessa);

	public ExtratoLancamento getExtratoCompleto();
	
	

}
