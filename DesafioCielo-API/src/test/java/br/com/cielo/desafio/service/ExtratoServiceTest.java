/**
 * DesafioCielo
 * 
 * @Author : Alessandro Billy
 */
package br.com.cielo.desafio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import br.com.cielo.desafio.model.ExtratoLancamento;
import br.com.cielo.desafio.repository.ExtratoRepository;

/**
 * @author Alessandro Billy
 *
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value=ExtratoService.class, secure = false)
public class ExtratoServiceTest {
	
	@Autowired
	protected MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

    @MockBean
    private ExtratoRepository extratoRepository;
    
	@Test
	public ExtratoLancamento getExtratoCompleto() {
		return extratoRepository.getExtratoCompleto();
	}



}
