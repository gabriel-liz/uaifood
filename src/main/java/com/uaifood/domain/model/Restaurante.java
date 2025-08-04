package com.uaifood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	 @Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	//@JoinColumn(name= "cozinha_id") em algum caso que o nome da coluna no banco seja diferente, fazemos assim pra relacioanar a coluna do codigo com a coluna do banco.
	private Cozinha cozinha;
	
	

}
