package br.com.gastos.gastosapi;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 
 * @author migue
 *
 * Representa um gasto realizado
 */
@Data
@Entity
public class Gasto {

	// Usando provisóriamente apenas para criar a estrutura inicial
	/**
	 * Identificador único do gasto
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * Código do usuário que realizou o gasto
	 */
	private long codigousuario;
	
	private String descricao;
	
	private double valor;
	
	private LocalDateTime data;
}
