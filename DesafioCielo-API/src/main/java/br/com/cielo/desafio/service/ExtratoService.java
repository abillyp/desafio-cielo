package br.com.cielo.desafio.service;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.model.ListaControleLancamento;
import br.com.cielo.desafio.repository.ExtratoRepository;

@Service
public class ExtratoService {
	
	@Autowired
	ExtratoRepository extratoRepository;


	public boolean deleteByNumeroRemessa(BigDecimal num) {
		return extratoRepository.deleteByNumeroRemessa(num);
	}

	public boolean updateByNumeroRemessa(ListaControleLancamento lancamento) {
		return extratoRepository.updateByNumeroRemessa(lancamento);
	}

	public ListaControleLancamento saveLancamento(@Valid ListaControleLancamento lanc, BigDecimal remessa) {
		return extratoRepository.saveLancamento(lanc, remessa);
	}

	/**
	 * @return
	 */
	public List<ListaControleLancamento> getLancamentosByRemessa(BigDecimal numeroRemessa) {
		return extratoRepository.getLancamentosByRemessa(numeroRemessa);
	}

	/**
	 * @return
	 */
	public ExtratoLancamento getExtratoCompleto() {
		return extratoRepository.getExtratoCompleto();
	}

	

}
