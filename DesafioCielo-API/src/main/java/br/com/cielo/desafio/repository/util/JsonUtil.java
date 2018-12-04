package br.com.cielo.desafio.repository.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import br.com.cielo.desafio.model.ExtratoLancamento;


/*
 * 
 * 
 * @Author Alessandro Billy
 * 
 * Classe utilitaria para trabalhar Serializacao
 * 
 * 
 */
@Component
public class JsonUtil {
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /*
     * Realiza a leitura do arquivo Json e faz o parse para o Objeto Extrato
     */
    public ExtratoLancamento extratoJsonFileRead(String fileName) {
    	
    	
    	ExtratoLancamento extrato = null;
    	try {
    	
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(
        	    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        extrato = objectMapper.readValue(new File(fileName), ExtratoLancamento.class);
    	}catch (IOException e) {
    		e.printStackTrace();
    		logger.info("Ocorreu uma erro ao ler o arquivo : "+fileName);
    	}
       
        return extrato;
    }
    
    /*
     * Realiza a leitura do arquivo Json, retornando a raiz do Json no formato JsonNode 
     */
 public JsonNode extratoJsonNodeFileRead(String fileName) {
    	
    	JsonNode rootNode = null;
    	try {
    	
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(
        	    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	        rootNode = objectMapper.readTree(new File(fileName));
    	}catch (IOException e) {
    		e.printStackTrace();
    		logger.info("Ocorreu uma erro ao ler o arquivo : "+fileName);
    	}
       
        return rootNode;
    }
    
 public JsonNode extratoJsonNodeFileWriter(String fileName, Object classOut) {
 	
 	JsonNode rootNode = null;
 	try {
 	
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
	        objectMapper.writeValue(new File(fileName), classOut);
	       logger.info("Arquivo gravado com sucesso ");
 	}catch (IOException e) {
 		e.printStackTrace();
 		logger.info("Ocorreu uma erro ao ler o arquivo : "+fileName);
 		rootNode =  null;
 	}
    
     return rootNode;
 }
    

}
