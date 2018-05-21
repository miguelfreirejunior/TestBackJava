package br.com.gastos.gastosapi;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.Data;

/**
 * 
 * @author migue
 *
 * Representa um gasto realizado
 */
@Data
@Table
public class Gasto {

	// Usando provisóriamente apenas para criar a estrutura inicial
	/**
	 * Identificador único do gasto
	 */
	@PrimaryKey
	private UUID id;
	
	/**
	 * Código do usuário que realizou o gasto
	 */
	private long codigousuario;
	
	private String descricao;
	
	private double valor;
	
	private LocalDateTime data;
}
