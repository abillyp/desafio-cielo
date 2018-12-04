package br.com.cielo.desafio.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.model.LancamentoContaCorrenteCliente;
import br.com.cielo.desafio.model.ListaControleLancamento;
import br.com.cielo.desafio.service.ExtratoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value="API - REST Extrato de Lançamentos")
@RestController
@RequestMapping("/cielo")
@CrossOrigin
public class ExtratoController {
	
	@Autowired
	private ExtratoService extratoService;
	
	@GetMapping("/extrato")
	@ApiOperation(value="Retorna o extrato completo de Lancamento")
	@ApiResponses(value= {
			@ApiResponse(
				code=200,
				message="OK",
				response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=204,
					message="Nenhum conteúdo",
					response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=500,
					message="Erro no Servidor",
					response=LancamentoContaCorrenteCliente.class)			
		})		
	public ResponseEntity<ExtratoLancamento> getExtratoCompleto() {
		ExtratoLancamento  extrato;
		extrato = extratoService.getExtratoCompleto();
		return extrato != null ? ResponseEntity.status(HttpStatus.OK).body(extrato) :
			ResponseEntity.status(HttpStatus.NO_CONTENT).body(extrato);
	}
	
	@GetMapping("/extrato/lancamentos")
	@ApiOperation(value="Retorna uma lista de lancamentos do extrato")
	@ApiResponses(value= {
			@ApiResponse(
				code=200,
				message="OK",
				response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=204,
					message="Nenhum conteúdo",
					response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=500,
					message="Erro no Servidor",
					response=LancamentoContaCorrenteCliente.class)			
		})		
	public ResponseEntity<List<ListaControleLancamento>> getExtratoLancamento() {
		List<ListaControleLancamento> lista = extratoService.getExtratoCompleto().getListaControleLancamento();
		return lista != null ? new ResponseEntity<>(lista, HttpStatus.OK)
							 :  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	
	
	
	
	@ApiOperation(value="Retorna uma Lancamento de acordo com a remessa informada")
	@GetMapping("/extrato/{remessa}")
	@ApiResponses(value= {
			@ApiResponse(
					code=200,
					message="OK",
					response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
						code=404,
						message="Nenhum conteúdo",
						response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
						code = 500, 
						message = "Erro no Servidor",
						response=LancamentoContaCorrenteCliente.class)
			})		
	public ResponseEntity<List<ListaControleLancamento>> getLancamentosByRemessa(@PathVariable BigDecimal remessa) {
		List<ListaControleLancamento> lista = extratoService.getLancamentosByRemessa(remessa);
		return lista != null ? new ResponseEntity<>(lista, HttpStatus.OK)
							 :  new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}	

	
	@PostMapping("/extrato/{remessa}")
	@ApiOperation(value="Salva um lançamento")
	@ApiResponses(value= {
			@ApiResponse(
					code=201,
					message="Created",
					response=LancamentoContaCorrenteCliente.class),		
			@ApiResponse(
					code=404,
					message="Nenhum conteúdo",
					response=LancamentoContaCorrenteCliente.class),			
			@ApiResponse(
					code=500,
					message="Erro no Servidor.",
					response=LancamentoContaCorrenteCliente.class)
	})		
	public ResponseEntity<?> saveLancamento(@RequestBody ListaControleLancamento lancamento, 
																					@PathVariable BigDecimal remessa) {
		ListaControleLancamento lancSalvo = extratoService.saveLancamento(lancamento, remessa);
		
		return lancSalvo != null ? ResponseEntity.status(HttpStatus.CREATED).body(lancSalvo) 
								: ResponseEntity.status(HttpStatus.NOT_FOUND).body(lancSalvo);
		
	}

	@PutMapping("/extrato/{remessa}")
	@ApiOperation(value="Atualiza um lançamento")
	@ApiResponses(value= {
			@ApiResponse(
				code=204,
				message="NO CONTENT",
				response=ListaControleLancamento.class),
			@ApiResponse(
					code=500,
					message="Erro do Servidor.",
					response=ListaControleLancamento.class)
	})		
	public ResponseEntity<?> updateByNumeroRemessa(@Valid @RequestBody ListaControleLancamento lanc, HttpServletResponse response) {
		if (extratoService.updateByNumeroRemessa(lanc) )
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(lanc);
		else
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
	}
	
	
	
	@DeleteMapping("/extrato/{remessa}")
	@ApiOperation(value="Deleta um lançamento")
	@ApiResponses(value= {
			@ApiResponse(
				code=204,
				message="NO CONTENT",
				response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=404,
					message="Remessa não encontrada.",
					response=LancamentoContaCorrenteCliente.class),
			@ApiResponse(
					code=500,
					message="Erro do Servidor.",
					response=LancamentoContaCorrenteCliente.class)
	})	
	public ResponseEntity<?> deleteByNumeroRemessa(@PathVariable BigDecimal remessa){
		if ( extratoService.deleteByNumeroRemessa(remessa)) 
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
		else 
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
	}
	
	
	
	
}
