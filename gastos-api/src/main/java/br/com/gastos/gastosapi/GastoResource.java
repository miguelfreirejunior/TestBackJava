package br.com.gastos.gastosapi;

import java.time.Instant;

import org.springframework.hateoas.ResourceSupport;

import lombok.Data;

/**
 * 
 * @author migue
 *
 * Recurso representando um gasto no sistema
 */
@Data
public class GastoResource extends ResourceSupport {	
	private long codigousuario;
	
	private String descricao;
	
	private double valor;
	
	private Instant data;
}
