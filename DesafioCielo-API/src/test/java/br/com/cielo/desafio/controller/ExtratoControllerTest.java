/**
 * DesafioCielo
 * 
 * @Author : Alessandro Billy
 */
package br.com.cielo.desafio.controller;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cielo.desafio.DesafioCieloApplication;
import br.com.cielo.desafio.model.DadosDomicilioBancario;
import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.model.LancamentoContaCorrenteCliente;
import br.com.cielo.desafio.model.ListaControleLancamento;

/**
 * @author Alessandro Billy
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes =  DesafioCieloApplication.class)
@AutoConfigureMockMvc
public class ExtratoControllerTest {

	@Autowired
	protected MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	ExtratoLancamento extrato;

    
	   @Before
	    public void setUp() throws Exception {

	    	try {
		    	mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

		    	
		    	DadosDomicilioBancario dadosDomicilioBancario = new DadosDomicilioBancario(); 
		    	
		    	dadosDomicilioBancario.setCodigoBanco(new BigDecimal("123"));
		    	dadosDomicilioBancario.setNumeroAgencia(new BigDecimal("1"));
		    	dadosDomicilioBancario.setNumeroContaCorrente("00000000065470");
		    	
		    	LancamentoContaCorrenteCliente lancamento = new LancamentoContaCorrenteCliente(); 
		    	lancamento.setNomeSituacaoRemessa("Pendente");
		    	lancamento.setNomeTipoOperacao("regular");
		    	lancamento.setNumeroRemessaBanco(new BigDecimal("64320236054"));
		    	
		    	lancamento.setDadosDomicilioBancario(dadosDomicilioBancario);
		    	
		    	ListaControleLancamento listaControle = new  ListaControleLancamento();
		    	
		    	listaControle.setDataEfetivaLancamento("30/11/2016");
		    	listaControle.setDataLancamentoContaCorrenteCliente("02/06/2016");
		    	
		    	listaControle.setDescricaoGrupoPagamento("LA-56");
		    	listaControle.setNomeBanco("BANCO ABCD S.A.             ");
		    	listaControle.setNumeroEvento(new Integer("42592397"));
		    	listaControle.setValorLancamentoRemessa(new BigDecimal("1960"));
		    	listaControle.setLancamentoContaCorrenteCliente(lancamento);
		    	
		    	
		    	List<ListaControleLancamento> lista = new ArrayList<ListaControleLancamento>();
		    	lista.add(listaControle);
		    	
		    	extrato = new ExtratoLancamento();
		    	extrato.setListaControleLancamento(lista);
		    	extrato.setIndice(new Integer("1"));
		    	extrato.setTamanhoPagina(new Integer("25"));
		    	extrato.setTotalElements(new Integer("39"));
		    	
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		throw e;
	    	}
	    	
	    }
	   
	   
		@After
		public void tearDown() throws Exception {
		}

		@Test
		public void testGetExtratoCompleto() throws Exception {
			try {
				
		        mvc.perform(get("/cielo/extrato").contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.listaControleLancamento[*].dataEfetivaLancamento").value(hasItem("03/06/2016")))
	            .andExpect(jsonPath("$.listaControleLancamento.[*].lancamentoContaCorrenteCliente.numeroRemessaBanco").value(hasItem(64320626054L)))
	            
	            .andExpect(jsonPath("$.listaControleLancamento[*].lancamentoContaCorrenteCliente.nomeTipoOperacao").value(hasItem("regular")))
	            .andExpect(jsonPath("$.listaControleLancamento[*].lancamentoContaCorrenteCliente.numeroRemessaBanco").value(hasItem(64320626054L)))
                .andExpect(jsonPath("$.listaControleLancamento[*].lancamentoContaCorrenteCliente.dadosDomicilioBancario.codigoBanco").value(hasItem(123)))
                .andExpect(jsonPath("$.listaControleLancamento[*].lancamentoContaCorrenteCliente.dadosDomicilioBancario.numeroAgencia").value(hasItem(1)))
                .andExpect(jsonPath("$.listaControleLancamento[*].lancamentoContaCorrenteCliente.dadosDomicilioBancario.numeroContaCorrente").value(hasItem("00000000065470")))
                .andExpect(jsonPath("$.listaControleLancamento[*].nomeBanco").value(hasItem("BANCO ABCD S.A.             ")))
                .andExpect(jsonPath("$.listaControleLancamento[*].valorLancamentoRemessa").value(hasItem(11499.1)))
	            .andReturn();
				}catch (Exception e) {
					e.printStackTrace();
					throw e;
				} 
		}
		
		@Test
		public void getLancamentosByRemessa() throws Exception {
			try {
				
		        mvc.perform(get("/cielo/extrato/{remessa}", "64320236054").contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$[*].lancamentoContaCorrenteCliente.nomeTipoOperacao").value(hasItem("regular")))
                .andExpect(jsonPath("$[*].nomeBanco").value(hasItem("BANCO ABCD S.A.             ")))
	            .andExpect(jsonPath("$[*].dataEfetivaLancamento").value(hasItem("03/06/2016")))
	            .andExpect(jsonPath("$[*].lancamentoContaCorrenteCliente.numeroRemessaBanco").value(hasItem(64320236054L)))
	            .andExpect(jsonPath("$[*].lancamentoContaCorrenteCliente.nomeTipoOperacao").value(hasItem("regular")))
                .andExpect(jsonPath("$[*].lancamentoContaCorrenteCliente.dadosDomicilioBancario.codigoBanco").value(hasItem(123)))
                .andExpect(jsonPath("$[*].lancamentoContaCorrenteCliente.dadosDomicilioBancario.numeroAgencia").value(hasItem(1)))
                .andExpect(jsonPath("$[*]..dadosDomicilioBancario.numeroContaCorrente").value(hasItem("00000000065470")))
                .andExpect(jsonPath("$[*].valorLancamentoRemessa").value(hasItem(11499.1)))

	            .andReturn();
				}catch (Exception e) {
					e.printStackTrace();
					throw e;
				} 
		}
		
		@Test
		public void saveLancamento() throws Exception {
			try {
				ObjectMapper mapper = new ObjectMapper();
		        mvc.perform(post("/cielo/extrato/{remessa}", "110000001")
		        		.contentType(MediaType.APPLICATION_JSON)
		        		.content(mapper.writeValueAsString(extrato)))
		        .andDo(print())	
		        .andExpect(status().isCreated())
	            .andReturn();
				}catch (Exception e) {
					e.printStackTrace();
					throw e;
				} 
		}	
		
		
		@Test
		public void updateByNumeroRemessa() throws Exception {
			try {
				ObjectMapper mapper = new ObjectMapper();

		        mvc.perform(put("/cielo/extrato/{remessa}", "64320236054")
		        		.contentType(MediaType.APPLICATION_JSON)
		        		.content(mapper.writeValueAsString(extrato.getListaControleLancamento().get(0))))
		        .andDo(print())	
		        .andExpect(status().isNoContent())
	            .andReturn();
				}catch (Exception e) {
					e.printStackTrace();
					throw e;
				} 
		}		
		
		@Test
		public void deleteByNumeroRemessa() throws Exception {
			try {
				ObjectMapper mapper = new ObjectMapper();

		        mvc.perform(delete("/cielo/extrato/{remessa}", "64320236054")
		        		.contentType(MediaType.APPLICATION_JSON)
		        		.content(mapper.writeValueAsString(extrato.getListaControleLancamento().get(0))))
		        .andDo(print())	
		        .andExpect(status().isNoContent())
	            .andReturn();
				}catch (Exception e) {
					e.printStackTrace();
					throw e;
				} 
		}		
		
}
