package com.uaifood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
