package br.com.gastos.gastosapi;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.hateoas.Identifiable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author migue
 *
 * Representa um gasto realizado
 */
@Data
@Table
@EqualsAndHashCode
public class Gasto implements Identifiable<UUID> {

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
	
	private Instant data;
}
