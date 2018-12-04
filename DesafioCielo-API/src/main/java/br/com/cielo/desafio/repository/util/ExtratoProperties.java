package br.com.cielo.desafio.repository.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:extrato.properties")
public class ExtratoProperties {
	

    @Value("${arquivo-entrada-extrato}")
    private String arquivoEntradaExtrato;
    
    @Value("${arquivo-saida-extrato}")
    private String arquivoSaidaExtrato;

	public String getArquivoEntradaExtrato() {
		return arquivoEntradaExtrato;
	}

	public void setArquivoEntradaExtrato(String arquivoEntradaExtrato) {
		this.arquivoEntradaExtrato = arquivoEntradaExtrato;
	}

	public String getArquivoSaidaExtrato() {
		return arquivoSaidaExtrato;
	}

	public void setArquivoSaidaExtrato(String arquivoSaidaExtrato) {
		this.arquivoSaidaExtrato = arquivoSaidaExtrato;
	}
    
    
    

	
}
