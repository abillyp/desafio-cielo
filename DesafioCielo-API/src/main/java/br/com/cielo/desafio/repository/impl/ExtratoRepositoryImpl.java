package br.com.cielo.desafio.repository.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.model.ListaControleLancamento;
import br.com.cielo.desafio.repository.ExtratoRepository;
import br.com.cielo.desafio.repository.util.ExtratoProperties;
import br.com.cielo.desafio.repository.util.JsonUtil;


@Component
public class ExtratoRepositoryImpl implements ExtratoRepository {
	
	// O caminho do arquivo Json foi configurado no arquivo extrato.properties
	@Autowired
	private ExtratoProperties extratoProperties;
	
	@Autowired
	private JsonUtil jsonUtil;

	/*
	 * Realiza o parse do Json para a classe do tipo DTO
	 */
	public ExtratoLancamento getExtratoCompleto() {
		ExtratoLancamento extratoLancamento = jsonUtil.extratoJsonFileRead(extratoProperties.getArquivoEntradaExtrato());
		return extratoLancamento;
	}
	
	
	/*
	 * Save um lancamento no arquivo
	 */
	public ListaControleLancamento saveLancamento(@Valid ListaControleLancamento lanc, BigDecimal remessa) {
		JsonNode listaControleLancamento = buscaNoControleLancamento();
		List<JsonNode> listNo = listaControleLancamento.findParents("lancamentoContaCorrenteCliente");
		
		/// Verifica se já existe o lancamento no arquivo
		if (existeLancamento(remessa, listNo )) 
			return null;
		
		ExtratoLancamento extrato = getExtratoCompleto();
		extrato.getListaControleLancamento().add(lanc);
        jsonUtil.extratoJsonNodeFileWriter(extratoProperties.getArquivoSaidaExtrato(), extrato);
        return lanc;
		
		
	}
	
	

	/* 
	 *  Retorna os lancamentos de uma determinada remessa 
	 */
	public List<ListaControleLancamento> getLancamentosByRemessa(BigDecimal numeroRemessa) {
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode listaControleLancamento = buscaNoControleLancamento();
		
		List<JsonNode> listNo = listaControleLancamento.findParents("lancamentoContaCorrenteCliente");
		// Busca todas as ocorrencias da remessa
		isolaLancamento(numeroRemessa, listNo, false);
        return mapper.convertValue(listNo, List.class);
		
	}


	/**
	 * Deleta todas os lançamento encontrados, utilizando como chave o numero da remessa passado
	 * 
	 * @return Indicativo de sucesso na remoção da Remessa
	 */
	public boolean deleteByNumeroRemessa(BigDecimal numeroRemessa) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(
				DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		JsonNode jsonNodeExtrato = jsonUtil.extratoJsonNodeFileRead(extratoProperties.getArquivoEntradaExtrato()); 
		
		List<JsonNode> listNo = jsonNodeExtrato.findParents("lancamentoContaCorrenteCliente");
		isolaLancamento(numeroRemessa, listNo, true);
		
		// Atribui ao Extrato as remessas
        ExtratoLancamento extrato = mapper.convertValue(jsonNodeExtrato, ExtratoLancamento.class);
        extrato.setListaControleLancamento(mapper.convertValue(listNo, List.class));
        
        jsonUtil.extratoJsonNodeFileWriter(extratoProperties.getArquivoSaidaExtrato(), extrato);
		return true;
		
	}


	/*
	 * Atualiza um registro no arquivo usando como chave o numero da remessa
	 */
	public boolean updateByNumeroRemessa(ListaControleLancamento lancamento) {

		JsonNode listaControleLancamento = buscaNoControleLancamento();
		List<JsonNode> listNo = listaControleLancamento.findParents("lancamentoContaCorrenteCliente");
		
		BigDecimal remessa;
		
		if ( lancamento.getLancamentoContaCorrenteCliente().getNumeroRemessaBanco() != null ) 
				remessa = lancamento.getLancamentoContaCorrenteCliente().getNumeroRemessaBanco();
		else
				return false; // não conseguiu recuperar o numero da remessa
			
	
		/// Verifica se já existe o lancamento no arquivo
		if (!existeLancamento(remessa, listNo )) 
			return false;
		
		ExtratoLancamento extrato = getExtratoCompleto();
		extrato.getListaControleLancamento().add(lancamento);
		this.deleteByNumeroRemessa(remessa);
        jsonUtil.extratoJsonNodeFileWriter(extratoProperties.getArquivoSaidaExtrato(), extrato);
        return true;
		
		
	}



	/**
	 * @return Nó contendo a lista de controle de Lancamento ou Null caso não exista
	 */
	private JsonNode buscaNoControleLancamento() {
		JsonNode jsonNode = jsonUtil.extratoJsonNodeFileRead(extratoProperties.getArquivoEntradaExtrato()); 
		return jsonNode.findValue("listaControleLancamento");
	}

	/**
	 * 
	 * Busca utilizada para apagar ou separar uma Lancamento conforme a remessa passada
	 * 
	 * 
	 * @param numeroRemessa
	 * @param listNo
	 * @param isolarRemessa 
	 * 			Caso = true, irá retornar somente os lancamentos que coincidam com o numero passado.
	 * 			Caso = false, irá retornar todos os lançamentos diferentes da Remessa passa como parãmentro.
	 */
	private List<JsonNode> isolaLancamento(BigDecimal numeroRemessa, List<JsonNode> listNo, boolean isolarRemessa) {
		Iterator<JsonNode> iterator = listNo.iterator();
		
		
		while (iterator.hasNext()) {
			JsonNode node = iterator.next();
			/*
			 * Isola a remessa
			 */
			// Verifica se a JsnoNode é igual remessa escolhida e se a remessa deve ser excluida da lista
			if ( node.findValue("numeroRemessaBanco").asText().equals(numeroRemessa.toString()) ) {
				// Remove o item de outra remesssa, retornando somente os lancamentos da remessa escolhida
				if (isolarRemessa) 	
					iterator.remove();
			} else 
				if (!isolarRemessa) 		
					iterator.remove();
				
		}
	return listNo;
	}
	
	
	/**
	 * Realiza a pesquisa de um Lancamento conforme a remessa passada
	 * @param remessa
	 * @param listNo
	 * @return boolean indicando TRUE em sucesso na pesquisa
	 */
	private boolean existeLancamento(BigDecimal remessa, List<JsonNode> listNo) {
		List<JsonNode> listaLancamentoNode = isolaLancamento(remessa, listNo, false);
		
		if (listaLancamentoNode.isEmpty() || listaLancamentoNode.size() >1 ) 
			return false;
		
		// Ja existe a remessa no arquivo
		if ( !listaLancamentoNode.get(0).findValue("numeroRemessaBanco").asText().equals(remessa.toString()) ) 
			return false;
		return true;
		
	}


}

