package com.uaifood.api.exceptionhandler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	
	private Integer status;
	private String type;
	private String tittle;
	private String detail;	
	
	private String userMessage;
	
	private LocalDateTime timestamp;	

}
